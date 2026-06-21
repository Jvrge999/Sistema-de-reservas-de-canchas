package cl.duoc.msreservas.repository;

import cl.duoc.msreservas.model.ReservaEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ReservaRepositoryTest {

    @Autowired
    private ReservaRepository repo;

    @Test
    void testGuardarReserva() {
        ReservaEntity entity = new ReservaEntity();
        entity.setIdCancha(10L);
        entity.setIdUsuario(5L);
        entity.setEstado("Pendiente");
        entity.setFecha(LocalDate.of(2026, 6, 21));
        entity.setHora(LocalTime.of(18, 0));

        ReservaEntity guardada = repo.save(entity);

        assertNotNull(guardada.getId());
        assertEquals("Pendiente", guardada.getEstado());
    }

    @Test
    void testBuscarPorId() {
        ReservaEntity entity = new ReservaEntity();
        entity.setEstado("Cancelada");
        ReservaEntity guardada = repo.save(entity);

        Optional<ReservaEntity> encontrada = repo.findById(guardada.getId());

        assertTrue(encontrada.isPresent());
    }
}