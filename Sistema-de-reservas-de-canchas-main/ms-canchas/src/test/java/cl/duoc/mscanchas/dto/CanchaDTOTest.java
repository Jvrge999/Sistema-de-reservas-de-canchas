package cl.duoc.mscanchas.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CanchaDTOTest {

    @Test
    void testDTOGettersYSetters() {
        CanchaDTO dto = new CanchaDTO();
        dto.setId(1L);
        dto.setNombre("Cancha Central");
        dto.setTipoPasto("Sintetico");
        dto.setCapacidad(14);
        dto.setPrecioHora(15000);

        assertEquals(1L, dto.getId());
        assertEquals("Cancha Central", dto.getNombre());
        assertEquals("Sintetico", dto.getTipoPasto());
        assertEquals(14, dto.getCapacidad());
        assertEquals(15000, dto.getPrecioHora());
    }
}