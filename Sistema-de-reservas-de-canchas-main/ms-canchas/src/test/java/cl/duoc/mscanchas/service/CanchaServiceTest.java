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
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

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
    void testGuardarExitoso() {
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Norte");
        dto.setTipoPasto("Sintético");
        dto.setCapacidad(14); 
        dto.setPrecioHora(25000.0); 

        CanchaEntity entity = new CanchaEntity();
        entity.setId(1L);
        entity.setNombre("Cancha Norte");
        entity.setTipoPasto("Sintético");
        entity.setCapacidad(14);
        entity.setPrecioHora(25000.0);

        when(repo.save(any(CanchaEntity.class))).thenReturn(entity);

        CanchaDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("Cancha Norte", resultado.getNombre());
    }

    @Test
    void testGuardarFallaReglaNegocioPasto() {
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Sur");
        dto.setCapacidad(14); // Gatilla la validación de Fútbol 7
        dto.setTipoPasto("Natural"); // Esto debería hacer que falle
        dto.setPrecioHora(25000.0);

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.guardar(dto);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testActualizarExitoso() {
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Editada");
        dto.setTipoPasto("Natural");
        dto.setCapacidad(22);
        dto.setPrecioHora(30000.0);

        CanchaEntity existente = new CanchaEntity();
        existente.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(CanchaEntity.class))).thenReturn(existente);

        CanchaDTO resultado = service.actualizar(1L, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).save(any(CanchaEntity.class));
    }

    @Test
    void testActualizarLanzaExcepcion404() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.actualizar(99L, new CanchaDTO());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testBorrarExitoso() {
        CanchaEntity existente = new CanchaEntity();
        existente.setId(1L);
        
        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        doNothing().when(repo).deleteById(1L);
        
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
    
    @Test
    void testBorrarLanzaExcepcion404() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.borrar(99L);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}