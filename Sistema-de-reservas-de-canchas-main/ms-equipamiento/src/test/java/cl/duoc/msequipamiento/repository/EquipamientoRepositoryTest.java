package cl.duoc.msequipamiento.repository;

import cl.duoc.msequipamiento.model.EquipamientoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class EquipamientoRepositoryTest {

    @Autowired
    private EquipamientoRepository repo;

    @Test
    void testGuardarEquipamiento() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setNombre("Mallas");
        entity.setTipo("Voleibol");
        entity.setPrecioArriendo(2500.0);
        entity.setDisponible(true);

        EquipamientoEntity guardado = repo.save(entity);

        assertNotNull(guardado.getId());
        assertEquals("Mallas", guardado.getNombre());
    }

    @Test
    void testBuscarPorId() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setNombre("Conos");
        EquipamientoEntity guardado = repo.save(entity);

        Optional<EquipamientoEntity> encontrado = repo.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("Conos", encontrado.get().getNombre());
    }

    @Test
    void testListarTodos() {
        EquipamientoEntity e1 = new EquipamientoEntity();
        e1.setNombre("Peto 1");
        repo.save(e1);

        EquipamientoEntity e2 = new EquipamientoEntity();
        e2.setNombre("Peto 2");
        repo.save(e2);

        List<EquipamientoEntity> lista = repo.findAll();

        assertEquals(2, lista.size());
    }
}