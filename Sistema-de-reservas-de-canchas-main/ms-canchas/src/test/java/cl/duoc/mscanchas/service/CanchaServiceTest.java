package cl.duoc.mscanchas.service;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
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
class CanchaServiceTest {

    @Mock
    private CanchaRepository repo;

    @InjectMocks
    private CanchaService service;

    @Test
    void testListarTodas() {
        CanchaEntity c1 = new CanchaEntity();
        c1.setId(1L);
        c1.setNombre("Cancha Central");

        when(repo.findAll()).thenReturn(Arrays.asList(c1));

        List<CanchaEntity> resultado = service.listarTodas();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Nueva Cancha");
        dto.setTipoPasto("Sintetico");
        dto.setCapacidad(10);
        dto.setPrecioHora(12000);

        CanchaEntity entityGuardada = new CanchaEntity();
        entityGuardada.setId(1L);
        entityGuardada.setNombre("Nueva Cancha");

        when(repo.save(any(CanchaEntity.class))).thenReturn(entityGuardada);

        CanchaEntity resultado = service.guardar(dto);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nueva Cancha", resultado.getNombre());
        verify(repo, times(1)).save(any(CanchaEntity.class));
    }

    @Test
    void testActualizarExitoso() {
        Long id = 1L;
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Actualizada");
        dto.setTipoPasto("Sintetico");
        dto.setCapacidad(14);
        dto.setPrecioHora(15000);

        CanchaEntity entidadExistente = new CanchaEntity();
        entidadExistente.setId(id);
        entidadExistente.setNombre("Vieja Cancha");

        when(repo.findById(id)).thenReturn(Optional.of(entidadExistente));
        when(repo.save(any(CanchaEntity.class))).thenReturn(entidadExistente); 

        CanchaEntity resultado = service.actualizar(id, dto);

        assertNotNull(resultado);
        assertEquals("Cancha Actualizada", resultado.getNombre());
        verify(repo, times(1)).findById(id);
        verify(repo, times(1)).save(any(CanchaEntity.class));
    }

    @Test
    void testActualizarLanzaExcepcionCuandoNoExiste() {
        Long id = 99L;
        CanchaDTO dto = new CanchaDTO();
        when(repo.findById(id)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.actualizar(id, dto);
        });
        
        assertEquals("Cancha no encontrada", exception.getMessage());
        verify(repo, times(1)).findById(id);
        verify(repo, never()).save(any(CanchaEntity.class));
    }

    @Test
    void testBorrar() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        service.borrar(id);

        verify(repo, times(1)).deleteById(id);
    }
}