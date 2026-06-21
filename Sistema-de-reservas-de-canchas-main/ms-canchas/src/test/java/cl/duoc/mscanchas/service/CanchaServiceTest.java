package cl.duoc.mscanchas.service;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
import cl.duoc.mscanchas.service.impl.CanchaServiceImpl;
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
    private CanchaServiceImpl service;

    @Test
    void testListarTodas() {
        CanchaEntity entity = new CanchaEntity();
        entity.setNombre("Cancha Central");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<CanchaDTO> resultado = service.listarTodas();

        assertEquals(1, resultado.size());
        assertEquals("Cancha Central", resultado.get(0).getNombre());
    }

   @Test
    void testGuardar() {
        
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Norte");
        dto.setCapacidad(14); // Ajustado a la realidad
        dto.setPrecioHora(25000.0); // Ajustado a la realidad

        CanchaEntity entity = new CanchaEntity();
        entity.setId(1L);
        entity.setNombre("Cancha Norte");
        entity.setCapacidad(14);
        entity.setPrecioHora(25000.0);

        when(repo.save(any(CanchaEntity.class))).thenReturn(entity);

        CanchaDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("Cancha Norte", resultado.getNombre());
    }

    @Test
    void testActualizarLanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> {
            service.actualizar(99L, new CanchaDTO());
        });
    }

    @Test
    void testBorrar() {
        doNothing().when(repo).deleteById(1L);
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}