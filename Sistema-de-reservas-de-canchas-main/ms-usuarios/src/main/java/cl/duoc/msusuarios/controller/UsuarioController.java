package cl.duoc.msusuarios.controller;

import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repo;

    @GetMapping
    public List<UsuarioEntity> listar() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public Optional<UsuarioEntity> obtenerPorId(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public UsuarioEntity guardar(@RequestBody UsuarioEntity u) {
        return repo.save(u);
    }
}
