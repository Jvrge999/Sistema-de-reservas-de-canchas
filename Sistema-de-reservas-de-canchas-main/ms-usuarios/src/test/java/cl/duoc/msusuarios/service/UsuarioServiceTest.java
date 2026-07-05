package cl.duoc.msusuarios.service;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import cl.duoc.msusuarios.service.impl.UsuarioServiceImpl;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioServiceImpl service;

    @Test
    void testListarTodos() {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre("Usuario Test");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<UsuarioDTO> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Usuario Test", resultado.get(0).getNombre());
    }

    @Test
    void testGuardarExitoso() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Jorge");
        dto.setEmail("jorge@realdomain.cl");

        UsuarioEntity entityGuardada = new UsuarioEntity();
        entityGuardada.setId(1L);
        entityGuardada.setNombre("Jorge");
        entityGuardada.setEmail("jorge@realdomain.cl");

        when(repo.save(any(UsuarioEntity.class))).thenReturn(entityGuardada);

        UsuarioDTO resultado = service.guardar(dto);

        assertEquals(1L, resultado.getId());
        assertEquals("Jorge", resultado.getNombre());
    }

    @Test
    void testGuardarFallaReglaNegocioDominio() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Hacker");
        dto.setEmail("hacker@yopmail.com"); // Gatilla la validación
        
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.guardar(dto);
        });
        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatusCode());
    }

    @Test
    void testActualizarExitoso() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Scarlet Editada");
        dto.setEmail("scarlet@realdomain.cl");

        UsuarioEntity existente = new UsuarioEntity();
        existente.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        when(repo.save(any(UsuarioEntity.class))).thenReturn(existente);

        UsuarioDTO resultado = service.actualizar(1L, dto);

        assertNotNull(resultado);
        verify(repo, times(1)).save(any(UsuarioEntity.class));
    }
    
    @Test
    void testActualizarFalla404() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.actualizar(99L, new UsuarioDTO());
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }

    @Test
    void testBorrarExitoso() {
        UsuarioEntity existente = new UsuarioEntity();
        existente.setId(1L);
        
        when(repo.findById(1L)).thenReturn(Optional.of(existente));
        doNothing().when(repo).deleteById(1L);
        
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
    
    @Test
    void testBorrarFalla404() {
        when(repo.findById(99L)).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            service.borrar(99L);
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
    }
}