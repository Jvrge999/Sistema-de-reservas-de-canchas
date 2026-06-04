package cl.duoc.msreservas.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;

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

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdCancha() { return idCancha; }
    public void setIdCancha(Long idCancha) { this.idCancha = idCancha; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }
    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}