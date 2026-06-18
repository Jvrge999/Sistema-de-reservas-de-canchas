package cl.duoc.msusuarios.dto;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioDTOTest {

    @Test
    void testDTOGettersYSetters() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(100L);
        dto.setNombre("Test User");
        dto.setEmail("test@correo.com");

        assertEquals(100L, dto.getId());
        assertEquals("Test User", dto.getNombre());
        assertEquals("test@correo.com", dto.getEmail());
    }
}