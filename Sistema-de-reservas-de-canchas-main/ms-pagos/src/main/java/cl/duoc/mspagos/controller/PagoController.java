package cl.duoc.mspagos.controller;

import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.service.PagoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
@Tag(name = "Pagos", description = "Operaciones CRUD para gestionar transacciones de pago")
public class PagoController {

    @Autowired
    private PagoService service;

    @Operation(summary = "Listar todos los pagos", description = "Retorna el historial completo de pagos registrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<PagoDTO>> listar() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Registrar nuevo pago")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Pago procesado y registrado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<PagoDTO> guardar(@Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(service.registrarPago(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar pago existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> actualizar(@PathVariable Long id, @Valid @RequestBody PagoDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar registro de pago")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Eliminación exitosa (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Transacción no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}