package cl.duoc.mscanchas.controller;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.service.CanchaService;
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

@WebMvcTest(CanchaController.class)
class CanchaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CanchaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarCanchasRetorna200() throws Exception {
        CanchaDTO dto = new CanchaDTO();
        dto.setNombre("Cancha Sur");
        when(service.listarTodas()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/canchas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Cancha Sur"));
    }

    @Test
    void testGuardarCanchaRetorna201() throws Exception {
        CanchaDTO inputDto = new CanchaDTO();
        inputDto.setNombre("Cancha VIP");
        inputDto.setTipoPasto("Sintetico");
        inputDto.setCapacidad(14);
        inputDto.setPrecioHora(25000.0);

        CanchaDTO outputDto = new CanchaDTO();
        outputDto.setId(10L);
        outputDto.setNombre("Cancha VIP");

        when(service.guardar(any(CanchaDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/canchas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }

    @Test
    void testBorrarCanchaRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/canchas/1"))
                .andExpect(status().isNoContent());
    }
}