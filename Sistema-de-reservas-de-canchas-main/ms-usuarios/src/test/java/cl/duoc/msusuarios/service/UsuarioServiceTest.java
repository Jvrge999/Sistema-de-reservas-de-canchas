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
        entity.setNombre("Pedro");
        when(repo.findAll()).thenReturn(Arrays.asList(entity));

        List<UsuarioDTO> resultado = service.listarTodos();

        assertEquals(1, resultado.size());
        assertEquals("Pedro", resultado.get(0).getNombre());
    }

    @Test
    void testGuardar() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Maria");
        dto.setEmail("maria@correo.cl");

        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(2L);
        entity.setNombre("Maria");

        when(repo.save(any(UsuarioEntity.class))).thenReturn(entity);

        UsuarioDTO resultado = service.guardar(dto);

        assertEquals(2L, resultado.getId());
        assertEquals("Maria", resultado.getNombre());
    }

    @Test
    void testActualizarLanzaExcepcion() {
        when(repo.findById(99L)).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> service.actualizar(99L, new UsuarioDTO()));
    }

    @Test
    void testBorrar() {
        doNothing().when(repo).deleteById(1L);
        service.borrar(1L);
        verify(repo, times(1)).deleteById(1L);
    }
}