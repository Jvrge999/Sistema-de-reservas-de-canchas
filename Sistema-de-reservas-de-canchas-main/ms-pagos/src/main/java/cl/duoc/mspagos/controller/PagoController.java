package cl.duoc.mspagos.controller;

import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.service.PagoService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    private static final Logger log = LoggerFactory.getLogger(PagoController.class);

    @Autowired
    private PagoService service;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> listarTodos() {
        log.info("Listando todos los pagos");
        return ResponseEntity.ok(service.listarTodos());
    }

    @PostMapping
    public ResponseEntity<PagoDTO> registrarPago(@Valid @RequestBody PagoDTO dto) {
        log.info("Iniciando registro de pago para reserva ID: {}", dto.getIdReserva());
        return ResponseEntity.status(HttpStatus.CREATED).body(service.registrarPago(dto));
    }
}
