package cl.duoc.mspagos.service;

import cl.duoc.mspagos.client.ReservaClient;
import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.dto.ReservaDTO;
import cl.duoc.mspagos.model.PagoEntity;
import cl.duoc.mspagos.repository.PagoRepository;
import cl.duoc.mspagos.service.impl.PagoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository repo;

    @Mock
    private ReservaClient reservaClient;

    @InjectMocks
    private PagoServiceImpl service;

    @Test
    void testListarTodos() {
        PagoEntity entity = new PagoEntity();
        entity.setMetodoPago("Tarjeta");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<PagoDTO> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Tarjeta", resultado.get(0).getMetodoPago());
    }

    @Test
    void testRegistrarPagoExitoso() {
        PagoDTO dto = new PagoDTO();
        dto.setIdReserva(10L);
        dto.setMonto(15000.0);
        dto.setMetodoPago("Efectivo");

        ReservaDTO reservaMock = new ReservaDTO();
        reservaMock.setId(10L);

        PagoEntity entityGuardada = new PagoEntity();
        entityGuardada.setId(1L);
        entityGuardada.setMetodoPago("Efectivo");
        entityGuardada.setEstado("COMPLETADO");

        // Simulamos que el Feign Client encuentra la reserva
        when(reservaClient.obtenerReserva(10L)).thenReturn(reservaMock);
        when(repo.save(any(PagoEntity.class))).thenReturn(entityGuardada);

        PagoDTO resultado = service.registrarPago(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("COMPLETADO", resultado.getEstado());
    }

    @Test
    void testRegistrarPagoFallaReservaNoExiste() {
        PagoDTO dto = new PagoDTO();
        dto.setIdReserva(99L);

        // Simulamos que el Feign Client NO encuentra la reserva
        when(reservaClient.obtenerReserva(99L)).thenReturn(null);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.registrarPago(dto);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testActualizarExitoso() {
        PagoDTO dto = new PagoDTO();
        dto.setIdReserva(1L);
        dto.setMonto(20000.0);
        dto.setMetodoPago("Transferencia");
        dto.setEstado("COMPLETADO");

        PagoEntity existente = new PagoEntity();
        existente.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(PagoEntity.class))).thenReturn(existente);

        PagoDTO resultado = service.actualizar(1L, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).save(any(PagoEntity.class));
    }

    @Test
    void testBorrarExitoso() {
        PagoEntity existente = new PagoEntity();
        existente.setId(1L);
        existente.setEstado("PENDIENTE"); // Se puede borrar
        
        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        doNothing().when(repo).deleteById(1L);
        
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
    
    @Test
    void testBorrarFallaReglaNegocioCompletado() {
        PagoEntity existente = new PagoEntity();
        existente.setId(1L);
        existente.setEstado("COMPLETADO"); // No se puede borrar

        when(repo.findById(1L)).thenReturn(Optional.of(existente));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.borrar(1L);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }
}