package cl.duoc.msreportes.controller;

import cl.duoc.msreportes.dto.ReporteDTO;
import cl.duoc.msreportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/reportes")
public class ReporteController {
    @Autowired private ReporteService service;

    @PostMapping
    public ReporteDTO generar(@RequestBody ReporteDTO dto) {
        return service.generarReporte(dto);
    }

    @GetMapping
    public List<ReporteDTO> historial() {
        return service.listarHistorial();
    }
}
