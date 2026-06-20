package cl.duoc.mspagos.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PagoEntityTest {

    @Test
    void testConstructoresYGettersSetters() {
        PagoEntity entidad = new PagoEntity();
        entidad.setId(2L);
        entidad.setIdReserva(200L);
        entidad.setMonto(25000.0);
        entidad.setMetodoPago("TRANSFERENCIA");
        entidad.setEstado("PENDIENTE");

        assertNotNull(entidad);
        assertEquals(2L, entidad.getId());
        assertEquals(200L, entidad.getIdReserva());
        assertEquals(25000.0, entidad.getMonto());
        assertEquals("TRANSFERENCIA", entidad.getMetodoPago());
        assertEquals("PENDIENTE", entidad.getEstado());
    }
}