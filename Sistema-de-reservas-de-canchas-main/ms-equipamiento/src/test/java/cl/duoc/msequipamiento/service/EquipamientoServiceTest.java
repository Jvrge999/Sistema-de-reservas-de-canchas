package cl.duoc.msequipamiento.service;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.model.EquipamientoEntity;
import cl.duoc.msequipamiento.repository.EquipamientoRepository;
import cl.duoc.msequipamiento.service.impl.EquipamientoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipamientoServiceTest {

    @Mock
    private EquipamientoRepository repo;

    // Aquí inyectamos el Impl para poder probar la lógica interna
    @InjectMocks
    private EquipamientoServiceImpl service; 

    @Test
    void testListarTodos() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setId(1L);
        entity.setNombre("Raqueta");
        entity.setTipo("Tenis");
        entity.setPrecioArriendo(5000.0);
        entity.setDisponible(true);

        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        // El servicio mapea la entidad a DTO internamente
        List<EquipamientoDTO> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Raqueta", resultado.get(0).getNombre());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        EquipamientoDTO dtoEntrada = new EquipamientoDTO();
        dtoEntrada.setNombre("Balon");
        dtoEntrada.setTipo("Futbol");
        dtoEntrada.setPrecioArriendo(3000.0);
        dtoEntrada.setDisponible(true);

        EquipamientoEntity entityGuardada = new EquipamientoEntity();
        entityGuardada.setId(1L);
        entityGuardada.setNombre("Balon");
        entityGuardada.setTipo("Futbol");
        entityGuardada.setPrecioArriendo(3000.0);
        entityGuardada.setDisponible(true);

        when(repo.save(any(EquipamientoEntity.class))).thenReturn(entityGuardada);

        EquipamientoDTO resultado = service.guardar(dtoEntrada);

        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Balon", resultado.getNombre());
        verify(repo, times(1)).save(any(EquipamientoEntity.class));
    }

    @Test
    void testEliminar() {
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        service.eliminar(id);

        verify(repo, times(1)).deleteById(id);
    }
}