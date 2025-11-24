import React, { useState, useEffect } from 'react';
import api from '../../services/api';

export default function AdminReservations() {
  const [reservations, setReservations] = useState([]);
  const [error, setError] = useState('');
  const [success, setSuccess] = useState('');

  useEffect(() => {
    loadReservations();
  }, []);

  const loadReservations = async () => {
    try {
      const response = await api.get('/admin/reservas');
      setReservations(response.data);
    } catch (err) {
      setError('Error al cargar reservas');
    }
  };

  const handleDelete = async (id) => {
    if (!window.confirm('¿Estás seguro de eliminar esta reserva?')) return;
    
    setError('');
    setSuccess('');
    
    try {
      await api.delete(`/admin/reservas/${id}`);
      setSuccess('Reserva eliminada exitosamente');
      loadReservations();
    } catch (err) {
      setError(err.response?.data?.error || 'Error al eliminar reserva');
    }
  };

  const getStatusBadge = (status) => {
    const badges = {
      ACTIVA: 'badge-success',
      MODIFICADA: 'badge-info',
      CANCELADA: 'badge-danger',
      COMPLETADA: 'badge-warning'
    };
    return <span className={`badge ${badges[status]}`}>{status}</span>;
  };

  return (
    <div>
      <h2>Gestión de Reservas</h2>
      {error && <div className="error">{error}</div>}
      {success && <div className="success">{success}</div>}

      <div className="card">
        <table>
          <thead>
            <tr>
              <th>Fecha</th>
              <th>Horario</th>
              <th>Laboratorio</th>
              <th>Equipo</th>
              <th>Estudiante</th>
              <th>Estado</th>
              <th>Acciones</th>
            </tr>
          </thead>
          <tbody>
            {reservations.length === 0 ? (
              <tr>
                <td colSpan="7" style={{ textAlign: 'center', padding: '40px', color: '#666' }}>
                  No hay reservas en el sistema
                </td>
              </tr>
            ) : (
              reservations.map(reservation => (
                <tr key={reservation.id}>
                  <td>{reservation.date}</td>
                  <td>{reservation.startTime} - {reservation.endTime}</td>
                  <td>{reservation.labId}</td>
                  <td>{reservation.equipmentId}</td>
                  <td>{reservation.studentId}</td>
                  <td>{getStatusBadge(reservation.status)}</td>
                  <td>
                    <button 
                      onClick={() => handleDelete(reservation.id)} 
                      className="btn-danger"
                      style={{ padding: '6px 12px', fontSize: '12px' }}
                    >
                      Eliminar
                    </button>
                  </td>
                </tr>
              ))
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
}
