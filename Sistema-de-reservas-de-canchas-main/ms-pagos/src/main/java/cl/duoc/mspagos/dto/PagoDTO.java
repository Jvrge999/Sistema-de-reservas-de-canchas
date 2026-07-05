package cl.duoc.mspagos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PagoDTO {

    private Long id;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long idReserva;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1, message = "El monto debe ser mayor a 0")
    private Double monto;

    @NotBlank(message = "El metodo de pago es obligatorio")
    private String metodoPago;

    @NotBlank(message = "El estado del pago es obligatorio")
    private String estado;
}