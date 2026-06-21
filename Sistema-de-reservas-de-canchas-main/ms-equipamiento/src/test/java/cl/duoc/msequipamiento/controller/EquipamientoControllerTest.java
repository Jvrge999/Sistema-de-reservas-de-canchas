package cl.duoc.msequipamiento.controller;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.service.EquipamientoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EquipamientoController.class)
class EquipamientoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EquipamientoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarEquipamientoRetorna200() throws Exception {
        EquipamientoDTO dto = new EquipamientoDTO();
        dto.setNombre("Balon");
        when(service.listarTodos()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/equipamiento")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Balon"));
    }

    @Test
    void testGuardarEquipamientoRetorna200() throws Exception {
        EquipamientoDTO inputDto = new EquipamientoDTO();
        inputDto.setNombre("Raqueta");

        EquipamientoDTO outputDto = new EquipamientoDTO();
        outputDto.setId(1L);
        outputDto.setNombre("Raqueta");

        when(service.guardar(any(EquipamientoDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/equipamiento")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void testRutaInexistenteRetorna404() throws Exception {
        mockMvc.perform(get("/ruta-falsa"))
                .andExpect(status().isNotFound());
    }
}