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
    void testListarRetorna200() throws Exception {
        UsuarioDTO dto = new UsuarioDTO();
        dto.setNombre("Carlos");
        when(service.listarTodos()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Carlos"));
    }

    @Test
    void testGuardarRetorna201() throws Exception {
        UsuarioDTO inputDto = new UsuarioDTO();
        inputDto.setNombre("Ana");
        inputDto.setEmail("ana@correo.cl");

        UsuarioDTO outputDto = new UsuarioDTO();
        outputDto.setId(5L);
        outputDto.setNombre("Ana");

        when(service.guardar(any(UsuarioDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    void testBorrarRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/usuarios/1"))
                .andExpect(status().isNoContent());
    }
}