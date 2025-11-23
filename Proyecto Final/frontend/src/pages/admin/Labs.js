import React, { useState, useEffect } from 'react';
import api from '../../services/api';

export default function Labs() {
  const [labs, setLabs] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [showScheduleModal, setShowScheduleModal] = useState(false);
  const [selectedLab, setSelectedLab] = useState(null);
  const [formData, setFormData] = useState({ code: '', name: '', capacity: '' });
  const [scheduleSlots, setScheduleSlots] = useState([]);
  const [error, setError] = useState('');

  useEffect(() => {
    loadLabs();
  }, []);

  const loadLabs = async () => {
    try {
      const response = await api.get('/admin/labs');
      setLabs(response.data);
    } catch (err) {
      setError('Error al cargar laboratorios');
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await api.post('/admin/labs', formData);
      setShowModal(false);
      setFormData({ code: '', name: '', capacity: '' });
      loadLabs();
    } catch (err) {
      setError(err.response?.data?.error || 'Error al crear laboratorio');
    }
  };

  const handleUpdateCapacity = async (id, capacity) => {
    try {
      await api.put(`/admin/labs/${id}/capacity/${capacity}`);
      loadLabs();
    } catch (err) {
      setError('Error al actualizar aforo');
    }
  };

  const openScheduleModal = (lab) => {
    setSelectedLab(lab);
    // Verificar si lab.schedule existe y tiene elementos
    const hasSchedule = lab.schedule && Array.isArray(lab.schedule) && lab.schedule.length > 0;
    setScheduleSlots(hasSchedule ? lab.schedule : [
      { dayOfWeek: 'MONDAY', start: '08:00', end: '17:00' }
    ]);
    setShowScheduleModal(true);
  };

  const addScheduleSlot = () => {
    setScheduleSlots([...scheduleSlots, { dayOfWeek: 'MONDAY', start: '08:00', end: '17:00' }]);
  };

  const updateScheduleSlot = (index, field, value) => {
    const updated = [...scheduleSlots];
    updated[index][field] = value;
    setScheduleSlots(updated);
  };

  const removeScheduleSlot = (index) => {
    setScheduleSlots(scheduleSlots.filter((_, i) => i !== index));
  };

  const handleScheduleSubmit = async () => {
    try {
      await api.put(`/admin/labs/${selectedLab.id}/schedule`, scheduleSlots);
      setShowScheduleModal(false);
      loadLabs();
    } catch (err) {
      setError('Error al configurar horario');
    }
  };

  return (
    <div>
      <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '20px' }}>
        <h2>Gestión de Laboratorios</h2>
        <button onClick={() => setShowModal(true)} className="btn-primary">
          + Nuevo Laboratorio
        </button>
      </div>

      {error && <div className="error">{error}</div>}

      <div className="card">
        <table>
          <thead>
            <tr>
              <th>Código</th>
              <th>Nombre</th>
              <th>Aforo</th>
              <th>Horarios</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {labs.map(lab => (
              <tr key={lab.id}>
                <td>{lab.code}</td>
                <td>{lab.name}</td>
                <td>
                  <input
                    type="number"
                    value={lab.capacity}
                    onChange={(e) => handleUpdateCapacity(lab.id, e.target.value)}
                    style={{ width: '80px' }}
                  />
                </td>
                <td>{lab.schedule && lab.schedule.length > 0 ? `${lab.schedule.length} configurados` : 'Sin configurar'}</td>
                <td>
                  <button onClick={() => openScheduleModal(lab)} className="btn-secondary">
                    Configurar Horario
                  </button>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>

      {showModal && (
        <div className="modal-overlay" onClick={() => setShowModal(false)}>
          <div className="modal" onClick={(e) => e.stopPropagation()}>
            <h2>Nuevo Laboratorio</h2>
            <form onSubmit={handleSubmit}>
              <div className="form-group">
                <label>Código</label>
                <input
                  value={formData.code}
                  onChange={(e) => setFormData({ ...formData, code: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label>Nombre</label>
                <input
                  value={formData.name}
                  onChange={(e) => setFormData({ ...formData, name: e.target.value })}
                  required
                />
              </div>
              <div className="form-group">
                <label>Aforo</label>
                <input
                  type="number"
                  value={formData.capacity}
                  onChange={(e) => setFormData({ ...formData, capacity: e.target.value })}
                  required
                />
              </div>
              <div className="modal-actions">
                <button type="button" onClick={() => setShowModal(false)} className="btn-secondary">
                  Cancelar
                </button>
                <button type="submit" className="btn-primary">Crear</button>
              </div>
            </form>
          </div>
        </div>
      )}

      {showScheduleModal && (
        <div className="modal-overlay" onClick={() => setShowScheduleModal(false)}>
          <div className="modal large" onClick={(e) => e.stopPropagation()}>
            <h2>Configurar Horario - {selectedLab.code}</h2>
            <p style={{ color: '#666', marginBottom: '20px' }}>
              Define los horarios en los que el laboratorio estará disponible para reservas.
            </p>
            
            {scheduleSlots.length === 0 && (
              <div style={{ padding: '20px', background: '#fff3cd', borderRadius: '6px', marginBottom: '15px' }}>
                <p style={{ margin: 0, color: '#856404' }}>
                  ⚠️ No hay horarios configurados. Agrega al menos un horario para que el laboratorio esté disponible.
                </p>
              </div>
            )}
            
            {scheduleSlots.map((slot, index) => (
              <div key={index} className="schedule-slot">
                <div className="schedule-header">
                  <span style={{ fontWeight: '500', color: '#333' }}>Horario #{index + 1}</span>
                  {scheduleSlots.length > 1 && (
                    <button 
                      type="button" 
                      onClick={() => removeScheduleSlot(index)} 
                      className="btn-danger"
                      style={{ padding: '4px 12px', fontSize: '12px' }}
                    >
                      ✕ Eliminar
                    </button>
                  )}
                </div>
                
                <div className="form-group">
                  <label>Día de la semana</label>
                  <select
                    value={slot.dayOfWeek}
                    onChange={(e) => updateScheduleSlot(index, 'dayOfWeek', e.target.value)}
                    style={{ width: '100%' }}
                  >
                    <option value="MONDAY">Lunes</option>
                    <option value="TUESDAY">Martes</option>
                    <option value="WEDNESDAY">Miércoles</option>
                    <option value="THURSDAY">Jueves</option>
                    <option value="FRIDAY">Viernes</option>
                    <option value="SATURDAY">Sábado</option>
                    <option value="SUNDAY">Domingo</option>
                  </select>
                </div>
                
                <div className="time-inputs">
                  <div className="form-group">
                    <label>Hora de inicio</label>
                    <input
                      type="time"
                      value={slot.start}
                      onChange={(e) => updateScheduleSlot(index, 'start', e.target.value)}
                      style={{ width: '100%' }}
                    />
                  </div>
                  <div className="form-group">
                    <label>Hora de fin</label>
                    <input
                      type="time"
                      value={slot.end}
                      onChange={(e) => updateScheduleSlot(index, 'end', e.target.value)}
                      style={{ width: '100%' }}
                    />
                  </div>
                </div>
              </div>
            ))}
            
            <button 
              type="button" 
              onClick={addScheduleSlot} 
              className="btn-secondary" 
              style={{ marginBottom: '20px', width: '100%' }}
            >
              + Agregar Nuevo Horario
            </button>
            
            <div className="modal-actions">
              <button onClick={() => setShowScheduleModal(false)} className="btn-secondary">
                Cancelar
              </button>
              <button onClick={handleScheduleSubmit} className="btn-primary">
                💾 Guardar Horarios
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
