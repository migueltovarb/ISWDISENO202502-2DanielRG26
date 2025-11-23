# 📅 Guía: Configuración de Horarios de Laboratorios

## ✅ Problema Resuelto

Se ha corregido y mejorado la funcionalidad de configuración de horarios de laboratorios que ya existía en el backend pero tenía problemas en el frontend.

---

## 🎯 ¿Qué hace esta funcionalidad?

Permite al **administrador** definir las franjas horarias en las que cada laboratorio estará **disponible para reservas**. Los estudiantes solo podrán hacer reservas dentro de estos horarios configurados.

---

## 🔧 Cambios Realizados

### 1. **Corrección de Bugs**
- ✅ Manejo correcto de laboratorios sin horarios configurados
- ✅ Validación de `lab.schedule` para evitar errores cuando es `null` o `undefined`
- ✅ Mensaje de advertencia cuando no hay horarios configurados

### 2. **Mejoras de UI/UX**
- ✅ Modal más grande y mejor organizado
- ✅ Estilos mejorados para los slots de horario
- ✅ Botones más intuitivos con iconos
- ✅ Indicador visual de horarios configurados en la tabla
- ✅ Mensaje de ayuda explicativo
- ✅ Diseño responsive

### 3. **Nuevos Estilos CSS**
- `.btn-danger` - Botón rojo para eliminar
- `.schedule-slot` - Contenedor de cada horario
- `.time-inputs` - Grid para hora inicio/fin
- `.schedule-header` - Encabezado de cada slot
- `.modal.large` - Modal más ancho para horarios

---

## 📖 Cómo Usar (Administrador)

### Paso 1: Acceder a Gestión de Laboratorios
1. Inicia sesión como **Administrador**
2. Ve a la sección **"Laboratorios"** en el dashboard

### Paso 2: Configurar Horarios
1. En la tabla de laboratorios, haz clic en **"Configurar Horario"**
2. Se abrirá un modal con los horarios actuales (o uno por defecto)

### Paso 3: Agregar Horarios
Para cada franja horaria:
- **Día de la semana**: Selecciona el día (Lunes a Domingo)
- **Hora de inicio**: Define cuándo abre el laboratorio (ej: 08:00)
- **Hora de fin**: Define cuándo cierra (ej: 17:00)

### Paso 4: Múltiples Horarios
- Haz clic en **"+ Agregar Nuevo Horario"** para agregar más franjas
- Puedes tener diferentes horarios para diferentes días
- Ejemplo:
  - Lunes: 08:00 - 12:00
  - Lunes: 14:00 - 18:00
  - Martes: 08:00 - 17:00

### Paso 5: Guardar
- Haz clic en **"💾 Guardar Horarios"**
- Los cambios se aplicarán inmediatamente

---

## 🔍 Ejemplos de Configuración

### Ejemplo 1: Horario Simple
```
Lunes a Viernes: 08:00 - 17:00
```
**Configuración:**
- Slot 1: MONDAY, 08:00 - 17:00
- Slot 2: TUESDAY, 08:00 - 17:00
- Slot 3: WEDNESDAY, 08:00 - 17:00
- Slot 4: THURSDAY, 08:00 - 17:00
- Slot 5: FRIDAY, 08:00 - 17:00

### Ejemplo 2: Horario con Descanso
```
Lunes: 08:00 - 12:00 y 14:00 - 18:00
```
**Configuración:**
- Slot 1: MONDAY, 08:00 - 12:00
- Slot 2: MONDAY, 14:00 - 18:00

### Ejemplo 3: Horario Extendido
```
Lunes a Jueves: 07:00 - 20:00
Viernes: 07:00 - 15:00
Sábado: 09:00 - 13:00
```
**Configuración:**
- Slot 1: MONDAY, 07:00 - 20:00
- Slot 2: TUESDAY, 07:00 - 20:00
- Slot 3: WEDNESDAY, 07:00 - 20:00
- Slot 4: THURSDAY, 07:00 - 20:00
- Slot 5: FRIDAY, 07:00 - 15:00
- Slot 6: SATURDAY, 09:00 - 13:00

---

## ⚠️ Validaciones Importantes

### En el Backend (ReservationService)
Cuando un estudiante intenta hacer una reserva, el sistema valida:

1. ✅ **Laboratorio abierto**: La reserva debe estar dentro de un horario configurado
2. ✅ **Día correcto**: El día de la reserva debe coincidir con un día configurado
3. ✅ **Hora válida**: La hora debe estar dentro del rango (start <= reserva <= end)

**Código de validación:**
```java
private void ensureLabOpen(Lab lab, LocalDate date, LocalTime start, LocalTime end) {
    DayOfWeek d = date.getDayOfWeek();
    boolean ok = lab.getSchedule().stream()
        .anyMatch(s -> s.getDayOfWeek() == d 
            && !start.isBefore(s.getStart()) 
            && !end.isAfter(s.getEnd()));
    if (!ok) throw new IllegalStateException("Laboratorio cerrado en ese horario");
}
```

---

## 🚫 Casos de Error

### Error 1: Sin Horarios Configurados
**Síntoma:** Estudiantes no pueden hacer reservas
**Solución:** Configurar al menos un horario para el laboratorio

### Error 2: Reserva Fuera de Horario
**Síntoma:** Error "Laboratorio cerrado en ese horario"
**Causa:** El estudiante intenta reservar en un día/hora no configurado
**Solución:** Verificar y ajustar los horarios del laboratorio

### Error 3: Horario Inválido
**Síntoma:** No se puede guardar el horario
**Causa:** Hora de inicio >= Hora de fin
**Solución:** Asegurar que inicio < fin

---

## 🔄 Flujo Completo

```
1. Admin crea laboratorio
   ↓
2. Admin configura horarios
   ↓
3. Admin agrega equipos
   ↓
4. Estudiante ve disponibilidad
   ↓
5. Estudiante intenta reservar
   ↓
6. Sistema valida horario ✅
   ↓
7. Reserva confirmada
```

---

## 📊 Visualización en la Tabla

La columna **"Horarios"** muestra:
- **"Sin configurar"** - No hay horarios (⚠️ laboratorio no disponible)
- **"3 configurados"** - Hay 3 franjas horarias definidas

---

## 🎨 Interfaz Mejorada

### Antes:
- Modal pequeño
- Sin validaciones visuales
- Difícil de usar

### Ahora:
- ✅ Modal grande y espacioso
- ✅ Mensaje de advertencia si no hay horarios
- ✅ Diseño limpio y organizado
- ✅ Botones intuitivos con iconos
- ✅ Fácil agregar/eliminar horarios

---

## 🧪 Cómo Probar

### Test 1: Crear Horario Básico
1. Crea un laboratorio (ej: LAB-001)
2. Haz clic en "Configurar Horario"
3. Configura: MONDAY, 08:00 - 17:00
4. Guarda
5. Verifica que aparezca "1 configurados"

### Test 2: Múltiples Horarios
1. Abre el modal de horarios
2. Agrega 3 horarios diferentes
3. Guarda
4. Verifica que aparezca "3 configurados"

### Test 3: Validación de Reserva
1. Como estudiante, intenta reservar en un día/hora configurado ✅
2. Intenta reservar fuera del horario ❌ (debe fallar)

---

## 📝 Notas Técnicas

### Formato de Datos

**Frontend → Backend:**
```json
[
  {
    "dayOfWeek": "MONDAY",
    "start": "08:00",
    "end": "17:00"
  }
]
```

**Backend (MongoDB):**
```json
{
  "schedule": [
    {
      "dayOfWeek": "MONDAY",
      "start": "08:00:00",
      "end": "17:00:00"
    }
  ]
}
```

### Días de la Semana (Enum)
```
MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
```

---

## ✅ Checklist de Implementación

- [x] Backend: Endpoint PUT `/admin/labs/{id}/schedule`
- [x] Backend: Validación en `ReservationService.ensureLabOpen()`
- [x] Frontend: Modal de configuración de horarios
- [x] Frontend: Manejo de laboratorios sin horarios
- [x] Frontend: Estilos mejorados
- [x] Frontend: Validación visual
- [x] Documentación completa

---

## 🎉 Resultado Final

Ahora los administradores pueden:
- ✅ Configurar horarios de apertura/cierre por día
- ✅ Definir múltiples franjas horarias por día
- ✅ Ver claramente qué laboratorios tienen horarios configurados
- ✅ Modificar horarios existentes fácilmente

Y los estudiantes:
- ✅ Solo pueden reservar en horarios válidos
- ✅ Reciben mensajes claros si intentan reservar fuera de horario

---

**¡La funcionalidad está completamente operativa! 🚀**
