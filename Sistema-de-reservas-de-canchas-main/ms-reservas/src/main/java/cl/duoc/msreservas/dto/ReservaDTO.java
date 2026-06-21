package cl.duoc.msreservas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class ReservaDTO {
    private Long id;

    @NotNull(message = "El ID de la cancha es obligatorio")
    private Long idCancha;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotNull(message = "La fecha de la reserva es obligatoria")
    private LocalDate fecha;

    @NotNull(message = "La hora de la reserva es obligatoria")
    private LocalTime hora;

    @NotBlank(message = "El estado de la reserva es obligatorio")
    private String estado;
}