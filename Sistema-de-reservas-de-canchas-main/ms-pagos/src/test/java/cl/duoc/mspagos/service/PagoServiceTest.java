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
        entity.setId(1L);
        entity.setIdReserva(100L);
        entity.setMonto(15000.0);

        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<PagoDTO> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals(15000.0, resultado.get(0).getMonto());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testRegistrarPagoExitoso() {
        // Given
        PagoDTO dtoEntrada = new PagoDTO();
        dtoEntrada.setIdReserva(100L);
        dtoEntrada.setMonto(15000.0);
        dtoEntrada.setMetodoPago("WEBPAY");

        ReservaDTO reservaMock = new ReservaDTO();
        reservaMock.setId(100L);

        PagoEntity entityGuardada = new PagoEntity();
        entityGuardada.setId(1L);
        entityGuardada.setIdReserva(100L);
        entityGuardada.setMonto(15000.0);
        entityGuardada.setMetodoPago("WEBPAY");
        entityGuardada.setEstado("COMPLETADO");

        // Mockeamos la validación del Feign Client
        when(reservaClient.obtenerReserva(100L)).thenReturn(reservaMock);
        // Mockeamos el guardado en BDD
        when(repo.save(any(PagoEntity.class))).thenReturn(entityGuardada);

        // When
        PagoDTO resultado = service.registrarPago(dtoEntrada);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("COMPLETADO", resultado.getEstado());
        verify(reservaClient, times(1)).obtenerReserva(100L);
        verify(repo, times(1)).save(any(PagoEntity.class));
    }

    @Test
    void testRegistrarPagoLanzaExcepcionPorReservaInexistente() {
        // Given
        PagoDTO dtoEntrada = new PagoDTO();
        dtoEntrada.setIdReserva(99L);

        // Simulamos que el Feign Client no encuentra la reserva
        when(reservaClient.obtenerReserva(99L)).thenReturn(null);

        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.registrarPago(dtoEntrada);
        });
        
        assertEquals("No se puede procesar el pago: La reserva no existe.", exception.getMessage());
        verify(reservaClient, times(1)).obtenerReserva(99L);
        // Verificamos que NUNCA se intente guardar en la base de datos si falla la validación
        verify(repo, never()).save(any(PagoEntity.class));
    }

    @Test
    void testActualizarExitoso() {
        Long id = 1L;
        PagoDTO dto = new PagoDTO();
        dto.setIdReserva(100L);
        dto.setMonto(20000.0);
        dto.setMetodoPago("EFECTIVO");
        dto.setEstado("COMPLETADO");

        PagoEntity entidadExistente = new PagoEntity();
        entidadExistente.setId(id);
        entidadExistente.setEstado("PENDIENTE");

        when(repo.findById(id)).thenReturn(Optional.of(entidadExistente));
        when(repo.save(any(PagoEntity.class))).thenReturn(entidadExistente); 

        PagoDTO resultado = service.actualizar(id, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).findById(id);
        verify(repo, times(1)).save(any(PagoEntity.class));
    }

    @Test
    void testBorrar() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        service.borrar(id);

        verify(repo, times(1)).deleteById(id);
    }
}