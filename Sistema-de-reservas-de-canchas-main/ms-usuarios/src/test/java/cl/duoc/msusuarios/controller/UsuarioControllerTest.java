package cl.duoc.msusuarios.controller;

import cl.duoc.msusuarios.dto.UsuarioDTO;
import cl.duoc.msusuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarUsuariosRetorna200() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Jorge Aguilera");
        when(service.listarTodos()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Jorge Aguilera"));
    }

    @Test
    void testGuardarUsuarioRetorna201() throws Exception {
        UsuarioDTO inputDto = new UsuarioDTO();
        inputDto.setNombre("Scarlet");
        inputDto.setEmail("scarlet@correo.cl");

        UsuarioDTO outputDto = new UsuarioDTO();
        outputDto.setId(1L);
        outputDto.setNombre("Scarlet");

        when(service.guardar(any(UsuarioDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testGuardarUsuarioFallaPorValidacionRetorna400() throws Exception {
        UsuarioDTO inputDto = new UsuarioDTO();
        // Faltan campos y el email tiene mal formato para forzar el @Valid
        inputDto.setNombre(""); 
        inputDto.setEmail("correo-sin-arroba"); 

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActualizarUsuarioRetorna200() throws Exception {
        UsuarioDTO inputDto = new UsuarioDTO();
        inputDto.setNombre("Jorge Editado");
        inputDto.setEmail("jorge.nuevo@correo.cl");

        when(service.actualizar(eq(1L), any(UsuarioDTO.class))).thenReturn(inputDto);

        mockMvc.perform(put("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testBorrarUsuarioRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}