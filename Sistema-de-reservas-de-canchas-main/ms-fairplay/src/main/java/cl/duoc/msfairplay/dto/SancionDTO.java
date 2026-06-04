package cl.duoc.msfairplay.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SancionDTO {

    private Long id;

    @NotNull(message = "El ID del usuario es obligatorio")
    private Long idUsuario;

    @NotBlank(message = "El motivo de la sancion es obligatorio")
    private String motivo;

    @NotNull(message = "El estado (activo/inactivo) es obligatorio")
    private Boolean activo;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Long idUsuario) { this.idUsuario = idUsuario; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public Boolean getActivo() { return activo; }
    public void setActivo(Boolean activo) { this.activo = activo; }
}