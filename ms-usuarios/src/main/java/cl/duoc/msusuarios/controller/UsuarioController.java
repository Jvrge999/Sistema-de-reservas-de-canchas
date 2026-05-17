package cl.duoc.msusuarios.controller;

import cl.duoc.msusuarios.service.UsuarioService;
import cl.duoc.msusuarios.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController 
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired 
    private UsuarioService service;

    // EL ENDPOINT VITAL PARA FEIGN CLIENT
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerUsuario(@PathVariable Long id) {
        UsuarioDTO dto = service.obtenerPorId(id);
        return ResponseEntity.ok(dto);
    }
}