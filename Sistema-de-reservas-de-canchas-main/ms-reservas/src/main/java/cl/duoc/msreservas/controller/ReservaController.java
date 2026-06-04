package cl.duoc.msreservas.controller;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<List<ReservaEntity>> listar() {
        return new ResponseEntity<>(service.listarTodas(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ReservaEntity> guardar(@Valid @RequestBody ReservaDTO dto) {
        // La validacion de Feign Client puede ir dentro del Service o ser llamada desde aqui
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaEntity> actualizar(@PathVariable Long id, @Valid @RequestBody ReservaDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}