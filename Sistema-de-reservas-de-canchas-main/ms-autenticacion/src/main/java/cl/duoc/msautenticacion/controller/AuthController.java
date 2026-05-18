package cl.duoc.msautenticacion.controller;

import cl.duoc.msautenticacion.dto.AuthDTO;
import cl.duoc.msautenticacion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/registrar")
    public AuthDTO registrar(@RequestBody AuthDTO dto) {
        return service.registrar(dto);
    }

    @PostMapping("/login")
    public AuthDTO login(@RequestBody AuthDTO dto) {
        return service.login(dto);
    }
}
