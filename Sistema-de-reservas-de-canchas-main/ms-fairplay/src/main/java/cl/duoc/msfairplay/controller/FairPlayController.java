package cl.duoc.msfairplay.controller;

import cl.duoc.msfairplay.dto.SancionDTO;
import cl.duoc.msfairplay.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/fairplay")
public class FairPlayController {

    @Autowired
    private SancionService service;

    @PostMapping("/sancionar")
    public SancionDTO sancionar(@RequestBody SancionDTO dto) {
        return service.registrarSancion(dto);
    }

    @GetMapping("/usuario/{id}")
    public List<SancionDTO> verSanciones(@PathVariable Long id) {
        return service.obtenerSancionesUsuario(id);
    }
}
