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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
    void testListarTodos() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setId(1L);
        entity.setNombre("Raqueta");
        entity.setTipo("Tenis");
        entity.setPrecioArriendo(5000.0);
        entity.setDisponible(true);

        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<EquipamientoDTO> resultado = service.listarTodos();

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Raqueta", resultado.get(0).getNombre());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGuardarNuevoExitoso() {
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
        verify(repo, times(1)).save(any(EquipamientoEntity.class));
    }

    @Test
    void testActualizarExitoso() {
        EquipamientoDTO dtoEntrada = new EquipamientoDTO();
        dtoEntrada.setId(1L);
        dtoEntrada.setNombre("Balon Actualizado");
        dtoEntrada.setTipo("Futbol");
        dtoEntrada.setPrecioArriendo(3500.0);
        dtoEntrada.setDisponible(true);

        EquipamientoEntity entityExistente = new EquipamientoEntity();
        entityExistente.setId(1L);
        entityExistente.setNombre("Balon Viejo");

        when(repo.findById(1L)).thenReturn(Optional.of(entityExistente));
        when(repo.save(any(EquipamientoEntity.class))).thenReturn(entityExistente);

        EquipamientoDTO resultado = service.guardar(dtoEntrada);

        assertNotNull(resultado);
        verify(repo, times(1)).findById(1L);
        verify(repo, times(1)).save(any(EquipamientoEntity.class));
    }

    @Test
    void testActualizarFallaNoEncontrado() {
        EquipamientoDTO dtoEntrada = new EquipamientoDTO();
        dtoEntrada.setId(99L); 
        
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.guardar(dtoEntrada);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testGuardarFallaReglaNegocioPrecioNegativo() {
        EquipamientoDTO dtoEntrada = new EquipamientoDTO();
        dtoEntrada.setNombre("Balon");
        dtoEntrada.setTipo("Futbol");
        dtoEntrada.setPrecioArriendo(-1000.0); // Gatilla el error
        dtoEntrada.setDisponible(true);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.guardar(dtoEntrada);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testEliminarExitoso() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setId(1L);
        entity.setDisponible(true); 

        when(repo.findById(1L)).thenReturn(Optional.of(entity));
        doNothing().when(repo).deleteById(1L);

        service.eliminar(1L);

        verify(repo, times(1)).deleteById(1L);
    }

    @Test
    void testEliminarFallaReglaNegocioEnUso() {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setId(1L);
        entity.setDisponible(false); // Gatilla el error

        when(repo.findById(1L)).thenReturn(Optional.of(entity));

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.eliminar(1L);
        });
        assertEquals(HttpStatus.CONFLICT, exception.getStatusCode());
    }
}