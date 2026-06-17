package cl.duoc.msusuarios.controller;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.model.UsuarioEntity;
import cl.duoc.msusuarios.service.UsuarioService;
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
@RequestMapping("/usuarios")
@Tag(name = "Usuarios", description = "Operaciones CRUD para gestionar los usuarios del sistema")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @Operation(summary = "Listar todos los usuarios", description = "Retorna la lista completa de usuarios registrados.")
    @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    @GetMapping
    public ResponseEntity<List<UsuarioEntity>> listar() {
        return new ResponseEntity<>(service.listarTodos(), HttpStatus.OK);
    }

    @Operation(summary = "Registrar nuevo usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Usuario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    })
    @PostMapping
    public ResponseEntity<UsuarioEntity> guardar(@Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.guardar(dto), HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar usuario existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Actualización exitosa"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioEntity> actualizar(@PathVariable Long id, @Valid @RequestBody UsuarioDTO dto) {
        return new ResponseEntity<>(service.actualizar(id, dto), HttpStatus.OK);
    }

    @Operation(summary = "Eliminar usuario")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Eliminación exitosa (Sin contenido)"),
        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        service.borrar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}