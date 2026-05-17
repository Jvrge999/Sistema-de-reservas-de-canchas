package cl.duoc.msusuarios.service;

import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import cl.duoc.msusuarios.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repo;

    // CONVIERTE LA ENTIDAD A DTO
    public UsuarioDTO obtenerPorId(Long id) {
        UsuarioEntity usuario = repo.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
        
        return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getEmail());
    }
}