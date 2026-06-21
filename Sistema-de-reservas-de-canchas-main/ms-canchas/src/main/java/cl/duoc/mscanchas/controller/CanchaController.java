package cl.duoc.mscanchas.controller;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.service.CanchaService;
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
@RequestMapping("/canchas")
@Tag(name = "Canchas", description = "Operaciones CRUD para gestionar las canchas")
public class CanchaController {

    @Autowired
    private CanchaService service;

    @Operation(summary = "Listar todas las canchas", description = "Retorna la lista completa de canchas registradas.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<CanchaDTO>> listar() {
        return new ResponseEntity<>(service.listarTodas(), HttpStatus.OK);
    }

    @Operation(summary = "Registrar nueva cancha")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Cancha creada exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<CanchaDTO> guardar(@Valid @RequestBody CanchaDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar cancha existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Cancha no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<CanchaDTO> actualizar(@PathVariable Long id, @Valid @RequestBody CanchaDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar cancha")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Eliminación exitosa (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Cancha no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}