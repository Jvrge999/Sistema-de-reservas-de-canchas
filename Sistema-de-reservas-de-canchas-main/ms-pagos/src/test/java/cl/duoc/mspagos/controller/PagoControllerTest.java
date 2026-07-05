package cl.duoc.mspagos.controller;

import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.service.PagoService;
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

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PagoService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testListarPagosRetorna200() throws Exception {
        PagoDTO dto = new PagoDTO();
        dto.setMetodoPago("Tarjeta");
        when(service.listarTodos()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/pagos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].metodoPago").value("Tarjeta"));
    }

    @Test
    void testGuardarPagoRetorna201() throws Exception {
        PagoDTO inputDto = new PagoDTO();
        inputDto.setIdReserva(1L);
        inputDto.setMonto(15000.0);
        inputDto.setMetodoPago("Efectivo");
        inputDto.setEstado("PENDIENTE");

        PagoDTO outputDto = new PagoDTO();
        outputDto.setId(5L);
        outputDto.setMetodoPago("Efectivo");

        when(service.registrarPago(any(PagoDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(5L));
    }

    @Test
    void testGuardarPagoFallaPorValidacionRetorna400() throws Exception {
        PagoDTO inputDto = new PagoDTO();
        // Faltan campos obligatorios para forzar el @Valid
        inputDto.setMonto(0.0); // Falla el @Min
        inputDto.setMetodoPago(""); // Falla el @NotBlank

        mockMvc.perform(post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testActualizarPagoRetorna200() throws Exception {
        PagoDTO inputDto = new PagoDTO();
        inputDto.setIdReserva(1L);
        inputDto.setMonto(20000.0);
        inputDto.setMetodoPago("Transferencia");
        inputDto.setEstado("COMPLETADO");

        when(service.actualizar(eq(1L), any(PagoDTO.class))).thenReturn(inputDto);

        mockMvc.perform(put("/pagos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isOk());
    }

    @Test
    void testBorrarPagoRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);
        mockMvc.perform(delete("/pagos/1"))
                .andExpect(status().isNoContent());
    }
}