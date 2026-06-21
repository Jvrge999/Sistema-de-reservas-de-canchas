package cl.duoc.msreservas.controller;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ReservaController.class)
class ReservaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservaService service;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    void testListarRetorna200() throws Exception {
        ReservaDTO dto = new ReservaDTO();
        dto.setEstado("Confirmada");
        when(service.listarTodas()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/reservas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("Confirmada"));
    }

    @Test
    void testGuardarRetorna201() throws Exception {
        ReservaDTO inputDto = new ReservaDTO();
        inputDto.setIdCancha(1L);
        inputDto.setIdUsuario(2L);
        inputDto.setFecha(LocalDate.of(2026, 6, 21));
        inputDto.setHora(LocalTime.of(18, 0));
        inputDto.setEstado("Pendiente");
        
        ReservaDTO outputDto = new ReservaDTO();
        outputDto.setId(100L);
        outputDto.setEstado("Pendiente");

        when(service.guardar(any(ReservaDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(100L));
    }

    @Test
    void testBorrarRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isNoContent());
    }
}