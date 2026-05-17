package cl.duoc.mscanchas.controller;

import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    @Autowired
    private CanchaRepository repo;

    // Obtener todas las canchas
    @GetMapping
    public List<CanchaEntity> listar() {
        return repo.findAll();
    }

    // Guardar una nueva cancha
    @PostMapping
    public CanchaEntity guardar(@RequestBody CanchaEntity cancha) {
        return repo.save(cancha);
    }

    // Borrar una cancha por ID
    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}