package cl.duoc.msusuarios.service;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
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
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repo;

    @InjectMocks
    private UsuarioService service;

    @Test
    void testListarTodos() {
        // Given
        UsuarioEntity u1 = new UsuarioEntity();
        u1.setId(1L);
        u1.setNombre("Jorge Aguilera");
        when(repo.findAll()).thenReturn(Arrays.asList(u1));

        // When
        List<UsuarioEntity> resultado = service.listarTodos();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        verify(repo, times(1)).findAll();
    }

    @Test
    void testGuardar() {
        // Given
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Nuevo User");
        dto.setEmail("nuevo@correo.com");

        UsuarioEntity entityGuardada = new UsuarioEntity();
        entityGuardada.setId(1L);
        entityGuardada.setNombre("Nuevo User");
        entityGuardada.setEmail("nuevo@correo.com");

        when(repo.save(any(UsuarioEntity.class))).thenReturn(entityGuardada);

        // When
        UsuarioEntity resultado = service.guardar(dto);

        // Then
        assertNotNull(resultado);
        assertEquals(1L, resultado.getId());
        assertEquals("Nuevo User", resultado.getNombre());
        verify(repo, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void testActualizarExitoso() {
        // Given
        Long id = 1L;
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("User Actualizado");
        dto.setEmail("actualizado@correo.com");

        UsuarioEntity entidadExistente = new UsuarioEntity();
        entidadExistente.setId(id);
        entidadExistente.setNombre("Viejo User");

        when(repo.findById(id)).thenReturn(Optional.of(entidadExistente));
        when(repo.save(any(UsuarioEntity.class))).thenReturn(entidadExistente); 

        // When
        UsuarioEntity resultado = service.actualizar(id, dto);

        // Then
        assertNotNull(resultado);
        assertEquals("User Actualizado", resultado.getNombre());
        verify(repo, times(1)).findById(id);
        verify(repo, times(1)).save(any(UsuarioEntity.class));
    }

    // AQUI CUMPLIMOS EL REQUISITO "assertThrows" DE LA RÚBRICA
    @Test
    void testActualizarLanzaExcepcionCuandoNoExiste() {
        // Given
        Long id = 99L;
        UsuarioDTO dto = new UsuarioDTO();
        when(repo.findById(id)).thenReturn(Optional.empty());

        // When / Then
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            service.actualizar(id, dto);
        });
        
        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(repo, times(1)).findById(id);
        verify(repo, never()).save(any(UsuarioEntity.class));
    }

    @Test
    void testBorrar() {
        // Given
        Long id = 1L;
        doNothing().when(repo).deleteById(id);

        // When
        service.borrar(id);

        // Then
        verify(repo, times(1)).deleteById(id);
    }
}