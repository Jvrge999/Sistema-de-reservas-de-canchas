package cl.duoc.mscanchas.controller;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.service.CanchaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/canchas")
public class CanchaController {

    @Autowired
    private CanchaService service;

    @GetMapping
    public ResponseEntity<List<CanchaEntity>> listar() {
        return new ResponseEntity<>(service.listarTodas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CanchaEntity> guardar(@Valid @RequestBody CanchaDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CanchaEntity> actualizar(@PathVariable Long id, @Valid @RequestBody CanchaDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}