package cl.duoc.msequipamiento.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EquipamientoDTOTest {

    @Test
    void testLombokGettersYSetters() {
        EquipamientoDTO dto = new EquipamientoDTO();
        dto.setId(10L);
        dto.setNombre("Raqueta Profesional");
        dto.setTipo("Tenis");
        dto.setPrecioArriendo(4500.0);
        dto.setDisponible(true);

        assertEquals(10L, dto.getId());
        assertEquals("Raqueta Profesional", dto.getNombre());
        assertEquals("Tenis", dto.getTipo());
        assertEquals(4500.0, dto.getPrecioArriendo());
        assertTrue(dto.getDisponible());
    }
}