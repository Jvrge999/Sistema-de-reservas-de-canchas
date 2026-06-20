package cl.duoc.msreservas.dto;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

class ReservaDTOTest {

    @Test
    void testDTOGettersYSetters() {
        ReservaDTO dto = new ReservaDTO();
        LocalDate fecha = LocalDate.of(2026, 12, 10);
        LocalTime hora = LocalTime.of(15, 30);

        dto.setId(1L);
        dto.setIdCancha(100L);
        dto.setIdUsuario(200L);
        dto.setFecha(fecha);
        dto.setHora(hora);
        dto.setEstado("CONFIRMADA");

        assertEquals(1L, dto.getId());
        assertEquals(100L, dto.getIdCancha());
        assertEquals(200L, dto.getIdUsuario());
        assertEquals(fecha, dto.getFecha());
        assertEquals(hora, dto.getHora());
        assertEquals("CONFIRMADA", dto.getEstado());
    }
}