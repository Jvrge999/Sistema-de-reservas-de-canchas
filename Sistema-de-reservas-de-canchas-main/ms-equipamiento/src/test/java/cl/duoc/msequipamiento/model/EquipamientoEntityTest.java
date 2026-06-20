package cl.duoc.msequipamiento.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquipamientoEntityTest {

    @Test
    void testConstructoresYGettersSetters() {
        EquipamientoEntity entidad = new EquipamientoEntity();
        entidad.setId(5L);
        entidad.setNombre("Balon Oficial");
        entidad.setTipo("Futbol");
        entidad.setPrecioArriendo(3000.0);
        entidad.setDisponible(false);

        assertNotNull(entidad);
        assertEquals(5L, entidad.getId());
        assertEquals("Balon Oficial", entidad.getNombre());
        assertEquals("Futbol", entidad.getTipo());
        assertEquals(3000.0, entidad.getPrecioArriendo());
        assertFalse(entidad.getDisponible());
    }
}