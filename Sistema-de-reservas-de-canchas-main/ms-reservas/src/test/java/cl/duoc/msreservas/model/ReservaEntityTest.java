package cl.duoc.msreservas.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservaEntityTest {

    @Test
    void testConstructoresYGettersSetters() {
        ReservaEntity entidad = new ReservaEntity();
        LocalDate fecha = LocalDate.of(2026, 11, 5);
        LocalTime hora = LocalTime.of(18, 0);

        entidad.setId(2L);
        entidad.setIdCancha(101L);
        entidad.setIdUsuario(201L);
        entidad.setFecha(fecha);
        entidad.setHora(hora);
        entidad.setEstado("PENDIENTE");

        assertNotNull(entidad);
        assertEquals(2L, entidad.getId());
        assertEquals(101L, entidad.getIdCancha());
        assertEquals(201L, entidad.getIdUsuario());
        assertEquals(fecha, entidad.getFecha());
        assertEquals(hora, entidad.getHora());
        assertEquals("PENDIENTE", entidad.getEstado());
    }
}