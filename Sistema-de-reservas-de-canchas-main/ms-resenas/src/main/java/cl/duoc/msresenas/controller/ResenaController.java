package cl.duoc.msresenas.controller;

import cl.duoc.msresenas.dto.ResenaDTO;
import cl.duoc.msresenas.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/resenas")
public class ResenaController {
    @Autowired private ResenaService service;

    @PostMapping
    public ResenaDTO crear(@RequestBody ResenaDTO dto) {
        return service.crear(dto);
    }

    @GetMapping
    public List<ResenaDTO> listar() {
        return service.listar();
    }
}
