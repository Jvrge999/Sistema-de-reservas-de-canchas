package cl.duoc.msequipamiento.controller;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.service.EquipamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipamiento")
public class EquipamientoController {

    @Autowired
    private EquipamientoService service;

    @GetMapping
    public List<EquipamientoDTO> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public EquipamientoDTO guardar(@RequestBody EquipamientoDTO dto) {
        return service.guardar(dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
