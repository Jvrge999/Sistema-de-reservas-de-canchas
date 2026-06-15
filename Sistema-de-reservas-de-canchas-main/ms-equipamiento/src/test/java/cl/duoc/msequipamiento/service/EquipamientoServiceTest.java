package cl.duoc.msequipamiento.service;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.model.EquipamientoEntity;
import cl.duoc.msequipamiento.repository.EquipamientoRepository;
import cl.duoc.msequipamiento.service.impl.EquipamientoServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EquipamientoServiceTest {

    @Mock
    private EquipamientoRepository repo;

    @InjectMocks
    private EquipamientoServiceImpl service;

    @Test
    @DisplayName("Debe retornar lista de equipamiento y convertirla a DTO")
    void debeListarTodos() {
        // Given (Arrange)
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setId(1L);
        entity.setNombre("Malla");
        entity.setDisponible(true);

        when(repo.findAll()).thenReturn(List.of(entity));

        // When (Act)
        List<EquipamientoDTO> resultado = service.listarTodos();

        // Then (Assert)
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Malla", resultado.get(0).getNombre());
        assertTrue(resultado.get(0).getDisponible());
        verify(repo, times(1)).findAll();
    }

    @Test
    @DisplayName("Debe guardar un equipamiento nuevo y retornar su DTO")
    void debeGuardarEquipamiento() {
        // Given (Arrange)
        EquipamientoDTO inputDto = new EquipamientoDTO();
        inputDto.setNombre("Conos");
        inputDto.setPrecioArriendo(500.0);

        EquipamientoEntity savedEntity = new EquipamientoEntity();
        savedEntity.setId(5L);
        savedEntity.setNombre("Conos");
        savedEntity.setPrecioArriendo(500.0);

        when(repo.save(any(EquipamientoEntity.class))).thenReturn(savedEntity);

        // When (Act)
        EquipamientoDTO resultado = service.guardar(inputDto);

        // Then (Assert)
        assertNotNull(resultado);
        assertEquals(5L, resultado.getId());
        assertEquals("Conos", resultado.getNombre());
    }
}