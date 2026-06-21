package cl.duoc.msusuarios.repository;

import cl.duoc.msusuarios.model.UsuarioEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository repo;

    @Test
    void testGuardarUsuario() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre("Lucia");
        entity.setEmail("lucia@duoc.cl");

        UsuarioEntity guardada = repo.save(entity);

        assertNotNull(guardada.getId());
        assertEquals("Lucia", guardada.getNombre());
    }

    @Test
    void testBuscarPorId() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre("Felipe");
        UsuarioEntity guardada = repo.save(entity);

        Optional<UsuarioEntity> encontrada = repo.findById(guardada.getId());

        assertTrue(encontrada.isPresent());
        assertEquals("Felipe", encontrada.get().getNombre());
    }
}