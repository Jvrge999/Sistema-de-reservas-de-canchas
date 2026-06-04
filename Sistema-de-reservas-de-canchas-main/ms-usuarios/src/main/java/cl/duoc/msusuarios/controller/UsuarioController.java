package cl.duoc.msusuarios.controller;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listar() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UsuarioEntity> guardar(@Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}