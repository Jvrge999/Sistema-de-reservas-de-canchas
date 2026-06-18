package cl.duoc.msusuarios.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UsuarioEntityTest {

    @Test
    void testConstructoresYGettersSetters() {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(2L);
        usuario.setNombre("Scarlet");
        usuario.setEmail("scarlet@correo.com");

        assertNotNull(usuario);
        assertEquals(2L, usuario.getId());
        assertEquals("Scarlet", usuario.getNombre());
        assertEquals("scarlet@correo.com", usuario.getEmail());
    }
}