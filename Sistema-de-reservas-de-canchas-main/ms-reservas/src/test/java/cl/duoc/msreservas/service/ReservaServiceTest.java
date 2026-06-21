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
        entity.setEstado("Activa");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<ReservaDTO> resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        assertEquals("Activa", resultado.get(0).getEstado());
    }

    @Test
    void testGuardar() {
        ReservaDTO dto = new ReservaDTO();
        dto.setEstado("Pendiente");

        ReservaEntity entity = new ReservaEntity();
        entity.setId(1L);
        entity.setEstado("Pendiente");

        when(repo.save(any(ReservaEntity.class))).thenReturn(entity);

        ReservaDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
    }

    @Test
    void testObtenerPorIdLanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.obtenerPorId(99L));
    }

    @Test
    void testBorrar() {
        doNothing().when(repo).deleteById(1L);
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}