package cl.duoc.msreservas.controller;

import cl.duoc.msreservas.dto.UsuarioDTO;
import cl.duoc.msreservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    // Endpoint de prueba para demostrar la comunicación Hito 2
    @GetMapping("/test-feign/{idUsuario}")
    public ResponseEntity<?> probarComunicacionFeign(@PathVariable Long idUsuario) {
        try {
            UsuarioDTO usuarioEncontrado = reservaService.validarUsuarioRemoto(idUsuario);
            return ResponseEntity.ok(usuarioEncontrado);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}