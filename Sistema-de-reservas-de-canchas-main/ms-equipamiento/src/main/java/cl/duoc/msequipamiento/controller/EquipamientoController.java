package cl.duoc.msequipamiento.controller;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.service.EquipamientoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/equipamiento")
@Tag(name = "Equipamiento", description = "Operaciones CRUD para gestionar el equipamiento deportivo")
public class EquipamientoController {

    @Autowired
    private EquipamientoService service;

    @Operation(summary = "Listar todo el equipamiento", description = "Retorna la lista completa del equipamiento registrado.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public List<EquipamientoDTO> listar() {
        return service.listarTodos();
    }

    @Operation(summary = "Registrar nuevo equipamiento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Equipamiento creado exitosamente"),
    @ApiResponse(responseCode = "400", description = "Datos de entrada invalidos")
    })
    @PostMapping
    public EquipamientoDTO guardar(@RequestBody EquipamientoDTO dto) {
        return service.guardar(dto);
    }

    @Operation(summary = "Actualizar equipamiento existente")
        @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
            @ApiResponse(responseCode = "404", description = "Equipamiento no encontrado")
        })
    @PutMapping("/{id}")
    public EquipamientoDTO actualizar(@PathVariable Long id, @RequestBody EquipamientoDTO dto) {
        dto.setId(id);
        return service.guardar(dto);
    }

    @Operation(summary = "Eliminar equipamiento")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Eliminación exitosa"),
        @ApiResponse(responseCode = "404", description = "Equipamiento no encontrado")
    })
    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}