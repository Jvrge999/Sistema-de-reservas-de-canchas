package cl.duoc.msusuarios.service.impl;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import cl.duoc.msusuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    @Override
    public List<UsuarioDTO> listarTodos() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO guardar(UsuarioDTO dto) {
        // Regla de Negocio: No se permiten correos de dominios temporales o de prueba por seguridad
        if (dto.getEmail().toLowerCase().endsWith("@test.com") || dto.getEmail().toLowerCase().endsWith("@yopmail.com")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: No se permiten correos de dominios temporales o de prueba.");
        }

        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        return convertirADTO(repo.save(entity));
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        UsuarioEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Usuario no encontrado para actualizar"));

        if (dto.getEmail().toLowerCase().endsWith("@test.com") || dto.getEmail().toLowerCase().endsWith("@yopmail.com")) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: No se permiten correos de dominios temporales o de prueba.");
        }

        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        return convertirADTO(repo.save(entity));
    }

    @Override
    public void borrar(Long id) {
        UsuarioEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Usuario no encontrado para eliminar"));
        
        repo.deleteById(id);
    }

    private UsuarioDTO convertirADTO(UsuarioEntity entity) {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setEmail(entity.getEmail());
        return dto;
    }
}