package cl.duoc.mspagos.repository;

import cl.duoc.mspagos.model.PagoEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PagoRepositoryTest {

    @Autowired
    private PagoRepository repo;

    @Test
    void testGuardarPago() {
        PagoEntity entity = new PagoEntity();
        entity.setIdReserva(101L);
        entity.setMonto(25000.0);
        entity.setMetodoPago("TRANSFERENCIA");
        entity.setEstado("COMPLETADO");

        PagoEntity guardado = repo.save(entity);

        assertNotNull(guardado.getId());
        assertEquals(25000.0, guardado.getMonto());
    }

    @Test
    void testBuscarPorId() {
        PagoEntity entity = new PagoEntity();
        entity.setEstado("PENDIENTE");
        PagoEntity guardado = repo.save(entity);

        Optional<PagoEntity> encontrado = repo.findById(guardado.getId());

        assertTrue(encontrado.isPresent());
        assertEquals("PENDIENTE", encontrado.get().getEstado());
    }

    @Test
    void testListarTodos() {
        PagoEntity p1 = new PagoEntity();
        p1.setMonto(5000.0);
        repo.save(p1);

        PagoEntity p2 = new PagoEntity();
        p2.setMonto(10000.0);
        repo.save(p2);

        List<PagoEntity> lista = repo.findAll();

        assertEquals(2, lista.size());
    }
}