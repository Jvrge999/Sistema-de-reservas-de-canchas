package cl.duoc.msequipamiento.controller;

import cl.duoc.msequipamiento.model.EquipamientoEntity;
import cl.duoc.msequipamiento.repository.EquipamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipamiento")
public class EquipamientoController {

    @Autowired
    private EquipamientoRepository repo;

    @GetMapping
    public List<EquipamientoEntity> listar() {
        return repo.findAll();
    }

    @PostMapping
    public EquipamientoEntity guardar(@RequestBody EquipamientoEntity e) {
        return repo.save(e);
    }
}