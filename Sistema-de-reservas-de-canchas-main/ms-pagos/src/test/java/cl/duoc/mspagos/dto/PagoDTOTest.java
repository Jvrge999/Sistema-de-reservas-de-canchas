package cl.duoc.mspagos.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PagoDTOTest {

    @Test
    void testDTOGettersYSetters() {
        PagoDTO dto = new PagoDTO();
        dto.setId(1L);
        dto.setIdReserva(100L);
        dto.setMonto(15000.0);
        dto.setMetodoPago("WEBPAY");
        dto.setEstado("COMPLETADO");

        assertEquals(1L, dto.getId());
        assertEquals(100L, dto.getIdReserva());
        assertEquals(15000.0, dto.getMonto());
        assertEquals("WEBPAY", dto.getMetodoPago());
        assertEquals("COMPLETADO", dto.getEstado());
    }
}