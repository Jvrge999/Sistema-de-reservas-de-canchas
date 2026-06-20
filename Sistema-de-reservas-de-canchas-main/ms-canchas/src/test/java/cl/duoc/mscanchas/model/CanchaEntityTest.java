package cl.duoc.mscanchas.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CanchaEntityTest {

    @Test
    void testConstructoresYGettersSetters() {
        CanchaEntity entidad = new CanchaEntity();
        entidad.setId(2L);
        entidad.setNombre("Cancha Norte");
        entidad.setTipoPasto("Natural");
        entidad.setCapacidad(22);
        entidad.setPrecioHora(20000);

        assertNotNull(entidad);
        assertEquals(2L, entidad.getId());
        assertEquals("Cancha Norte", entidad.getNombre());
        assertEquals("Natural", entidad.getTipoPasto());
        assertEquals(22, entidad.getCapacidad());
        assertEquals(20000, entidad.getPrecioHora());
    }
}