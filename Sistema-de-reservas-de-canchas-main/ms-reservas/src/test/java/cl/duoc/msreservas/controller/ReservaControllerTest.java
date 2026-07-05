package cl.duoc.msreservas.controller;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.service.ReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import static org.mockito.ArgumentMatchers.eq;
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

    @Test
    void testListarReservasRetorna200() throws Exception {
        ReservaDTO dto = new ReservaDTO();
        dto.setEstado("PENDIENTE");
        when(service.listarTodas()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/reservas")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PENDIENTE"));
    }

    @Test
    void testObtenerReservaPorIdRetorna200() throws Exception {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(1L);
        dto.setEstado("CONFIRMADA");

        when(service.obtenerPorId(1L)).thenReturn(dto);

        mockMvc.perform(get("/reservas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("CONFIRMADA"));
    }

    @Test
    void testGuardarReservaRetorna201() throws Exception {
        ReservaDTO inputDto = new ReservaDTO();
        inputDto.setIdCancha(1L);
        inputDto.setIdUsuario(2L);
        inputDto.setFecha(LocalDate.now().plusDays(1)); // Mañana
        inputDto.setHora(LocalTime.of(18, 0));
        inputDto.setEstado("PENDIENTE");

        ReservaDTO outputDto = new ReservaDTO();
        outputDto.setId(5L);
        outputDto.setEstado("PENDIENTE");

        when(service.guardar(any(ReservaDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    void testGuardarReservaFallaPorValidacionRetorna400() throws Exception {
        ReservaDTO inputDto = new ReservaDTO();
        // Faltan todos los campos obligatorios para forzar los @NotNull y @NotBlank

        mockMvc.perform(post("/reservas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActualizarReservaRetorna200() throws Exception {
        ReservaDTO inputDto = new ReservaDTO();
        inputDto.setIdCancha(1L);
        inputDto.setIdUsuario(2L);
        inputDto.setFecha(LocalDate.now().plusDays(2));
        inputDto.setHora(LocalTime.of(20, 0));
        inputDto.setEstado("CONFIRMADA");

        when(service.actualizar(eq(1L), any(ReservaDTO.class))).thenReturn(inputDto);

        mockMvc.perform(put("/reservas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testBorrarReservaRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/reservas/1"))
                .andExpect(status().isNoContent());
    }
}