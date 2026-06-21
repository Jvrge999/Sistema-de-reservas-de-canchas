package cl.duoc.msusuarios.service.impl;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import cl.duoc.msusuarios.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        return convertirADTO(repo.save(entity));
    }

    @Override
    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Optional<UsuarioEntity> existente = repo.findById(id);
        if (existente.isPresent()) {
            UsuarioEntity entity = existente.get();
            entity.setNombre(dto.getNombre());
            entity.setEmail(dto.getEmail());
            return convertirADTO(repo.save(entity));
        }
        throw new RuntimeException("Usuario no encontrado");
    }

    @Override
    public void borrar(Long id) {
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