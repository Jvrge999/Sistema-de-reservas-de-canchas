package cl.duoc.mscanchas.repository;

import cl.duoc.mscanchas.model.CanchaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CanchaRepositoryTest {

    @Autowired
    private CanchaRepository repo;

    @Test
    void testGuardarCancha() {
        CanchaEntity entity = new CanchaEntity();
        entity.setNombre("Cancha 5");
        entity.setPrecioHora(15000.0);

        CanchaEntity guardada = repo.save(entity);

        assertNotNull(guardada.getId());
        assertEquals("Cancha 5", guardada.getNombre());
    }

    @Test
    void testBuscarPorId() {
        CanchaEntity entity = new CanchaEntity();
        entity.setNombre("Cancha 7");
        CanchaEntity guardada = repo.save(entity);

        Optional<CanchaEntity> encontrada = repo.findById(guardada.getId());

        assertTrue(encontrada.isPresent());
        assertEquals("Cancha 7", encontrada.get().getNombre());
    }
}