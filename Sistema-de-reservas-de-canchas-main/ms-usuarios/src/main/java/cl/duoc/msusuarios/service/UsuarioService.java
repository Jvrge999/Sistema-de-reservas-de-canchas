package cl.duoc.msusuarios.service;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    public List<UsuarioEntity> listarTodos() {
        return repo.findAll();
    }

    public UsuarioEntity guardar(UsuarioDTO dto) {
        UsuarioEntity entity = new UsuarioEntity();
        entity.setNombre(dto.getNombre());
        entity.setEmail(dto.getEmail());
        return repo.save(entity);
    }

    public UsuarioEntity actualizar(Long id, UsuarioDTO dto) {
        Optional<UsuarioEntity> existente = repo.findById(id);
        
        if (existente.isPresent()) {
            UsuarioEntity entity = existente.get();
            entity.setNombre(dto.getNombre());
            entity.setEmail(dto.getEmail());
            return repo.save(entity);
        }
        throw new RuntimeException("Usuario no encontrado");
    }

    public void borrar(Long id) {
        repo.deleteById(id);
    }
}