package cl.duoc.msreservas.service;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReservaServiceTest {

    @Mock
    private ReservaRepository repo;

    @InjectMocks
    private ReservaService service;

    @Test
    void testListarTodas() {
        ReservaEntity r1 = new ReservaEntity();
        r1.setId(1L);
        r1.setEstado("CONFIRMADA");

        when(repo.findAll()).thenReturn(Arrays.asList(r1));

        List<ReservaEntity> resultado = service.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testObtenerPorIdExitoso() {
        ReservaEntity entidad = new ReservaEntity();
        entidad.setId(1L);
        when(repo.findById(1L)).thenReturn(Optional.of(entidad));

        ReservaEntity resultado = service.obtenerPorId(1L);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        verify(repo, times(1)).findById(1L);
    }

    @Test
    void testObtenerPorIdLanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.obtenerPorId(99L);
        });

        assertEquals("Reserva no encontrada", exception.getMessage());
        verify(repo, times(1)).findById(99L);
    }

    @Test
    void testGuardar() {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdCancha(10L);
        dto.setIdUsuario(20L);
        dto.setFecha(LocalDate.now());
        dto.setHora(LocalTime.now());
        dto.setEstado("CONFIRMADA");

        ReservaEntity entityGuardada = new ReservaEntity();
        entityGuardada.setId(1L);
        entityGuardada.setIdCancha(10L);
        entityGuardada.setEstado("CONFIRMADA");

        when(repo.save(any(ReservaEntity.class))).thenReturn(entityGuardada);

        ReservaEntity resultado = service.guardar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("CONFIRMADA", resultado.getEstado());
        verify(repo, times(1)).save(any(ReservaEntity.class));
    }

    @Test
    void testActualizarExitoso() {
        Long id = 1L;
        ReservaDTO dto = new ReservaDTO();
        dto.setEstado("CANCELADA");

        ReservaEntity entidadExistente = new ReservaEntity();
        entidadExistente.setId(id);
        entidadExistente.setEstado("CONFIRMADA");

        when(repo.findById(id)).thenReturn(Optional.of(entidadExistente));
        when(repo.save(any(ReservaEntity.class))).thenReturn(entidadExistente); 

        ReservaEntity resultado = service.actualizar(id, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).findById(id);
        verify(repo, times(1)).save(any(ReservaEntity.class));
    }

    @Test
    void testActualizarLanzaExcepcionCuandoNoExiste() {
        Long id = 99L;
        ReservaDTO dto = new ReservaDTO();
        when(repo.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.actualizar(id, dto);
        });
        
        assertEquals("Reserva no encontrada", exception.getMessage());
        verify(repo, times(1)).findById(id);
        verify(repo, never()).save(any(ReservaEntity.class));
    }

    @Test
    void testBorrar() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        service.borrar(id);

        verify(repo, times(1)).deleteById(id);
    }
}