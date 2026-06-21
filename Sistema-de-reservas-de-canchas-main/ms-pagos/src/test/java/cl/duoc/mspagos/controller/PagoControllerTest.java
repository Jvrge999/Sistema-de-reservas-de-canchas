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
        dto.setMetodoPago("WEBPAY");
        when(service.listarTodos()).thenReturn(Arrays.asList(dto));

        mockMvc.perform(get("/pagos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].metodoPago").value("WEBPAY"));
    }

    @Test
    void testGuardarPagoRetorna201() throws Exception {
        // Se llenan los campos obligatorios para pasar la validación @Valid
        PagoDTO inputDto = new PagoDTO();
        inputDto.setIdReserva(50L);
        inputDto.setMonto(15000.0);
        inputDto.setMetodoPago("DEBITO");
        inputDto.setEstado("COMPLETADO");

        PagoDTO outputDto = new PagoDTO();
        outputDto.setId(1L);
        outputDto.setIdReserva(50L);
        outputDto.setEstado("COMPLETADO");

        when(service.registrarPago(any(PagoDTO.class))).thenReturn(outputDto);

        mockMvc.perform(post("/pagos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(inputDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.estado").value("COMPLETADO"));
    }

    @Test
    void testBorrarPagoRetorna204() throws Exception {
        doNothing().when(service).borrar(1L);

        mockMvc.perform(delete("/pagos/1"))
                .andExpect(status().isNoContent());
    }
}