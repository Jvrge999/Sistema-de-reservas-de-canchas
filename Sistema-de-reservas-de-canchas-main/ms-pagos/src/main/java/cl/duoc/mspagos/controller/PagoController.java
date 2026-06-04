package cl.duoc.mspagos.controller;

import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.service.PagoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PagoDTO> guardar(@Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(service.registrarPago(dto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}