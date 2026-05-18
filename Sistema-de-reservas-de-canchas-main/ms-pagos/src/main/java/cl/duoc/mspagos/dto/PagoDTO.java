package cl.duoc.mspagos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {
    private Long id;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotNull(message = "El monto no puede ser nulo")
    @Min(value = 1000, message = "El monto minimo a pagar es 1000")
    private Double monto;

    private String metodoPago;
    private String estado;
}
