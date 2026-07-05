package cl.duoc.msreservas.service;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import cl.duoc.msreservas.service.impl.ReservaServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    private ReservaServiceImpl service;

    @Test
    void testListarTodas() {
        ReservaEntity entity = new ReservaEntity();
        entity.setEstado("PENDIENTE");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<ReservaDTO> resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        assertEquals("PENDIENTE", resultado.get(0).getEstado());
    }

    @Test
    void testObtenerPorIdExitoso() {
        ReservaEntity entity = new ReservaEntity();
        entity.setId(1L);
        entity.setEstado("CONFIRMADA");

        when(repo.findById(1L)).thenReturn(Optional.of(entity));

        ReservaDTO resultado = service.obtenerPorId(1L);

        assertEquals(1L, resultado.getId());
        assertEquals("CONFIRMADA", resultado.getEstado());
    }

    @Test
    void testObtenerPorIdFalla404() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.obtenerPorId(99L);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testGuardarExitoso() {
        ReservaDTO dto = new ReservaDTO();
        dto.setIdCancha(1L);
        dto.setIdUsuario(2L);
        dto.setFecha(LocalDate.now().plusDays(1)); // Fecha futura válida
        dto.setHora(LocalTime.of(15, 0));
        dto.setEstado("PENDIENTE");

        ReservaEntity entityGuardada = new ReservaEntity();
        entityGuardada.setId(1L);
        entityGuardada.setEstado("PENDIENTE");
        entityGuardada.setFecha(LocalDate.now().plusDays(1));

        when(repo.save(any(ReservaEntity.class))).thenReturn(entityGuardada);

        ReservaDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("PENDIENTE", resultado.getEstado());
    }

    @Test
    void testGuardarFallaReglaNegocioFechaPasada() {
        ReservaDTO dto = new ReservaDTO();
        dto.setFecha(LocalDate.now().minusDays(1)); // Ayer (Gatilla el error)
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.guardar(dto);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testActualizarExitoso() {
        ReservaDTO dto = new ReservaDTO();
        dto.setFecha(LocalDate.now().plusDays(2));
        dto.setEstado("CONFIRMADA");

        ReservaEntity existente = new ReservaEntity();
        existente.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(ReservaEntity.class))).thenReturn(existente);

        ReservaDTO resultado = service.actualizar(1L, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).save(any(ReservaEntity.class));
    }

    @Test
    void testBorrarExitoso() {
        ReservaEntity existente = new ReservaEntity();
        existente.setId(1L);
        existente.setEstado("PENDIENTE"); // Se puede borrar
        
        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        doNothing().when(repo).deleteById(1L);
        
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
    
    @Test
    void testBorrarFallaReglaNegocioConfirmada() {
        ReservaEntity existente = new ReservaEntity();
        existente.setId(1L);
        existente.setEstado("CONFIRMADA"); // No se puede borrar

        when(repo.findById(1L)).thenReturn(Optional.of(existente));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.borrar(1L);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }
}