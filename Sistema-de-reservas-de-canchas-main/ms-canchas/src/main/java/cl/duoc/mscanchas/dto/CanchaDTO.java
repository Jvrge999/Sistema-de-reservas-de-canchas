package cl.duoc.mscanchas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CanchaDTO {

    private Long id;

    @NotBlank(message = "El nombre de la cancha es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo de pasto es obligatorio")
    private String tipoPasto;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser mayor a 0")
    private Integer capacidad;

    @NotNull(message = "El precio por hora es obligatorio")
    @Min(value = 1, message = "El precio por hora no puede ser negativo")
    private Integer precioHora;

    // Genera los Getters y Setters (o usa @Data si tienes Lombok instalado)
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getTipoPasto() { return tipoPasto; }
    public void setTipoPasto(String tipoPasto) { this.tipoPasto = tipoPasto; }
    public Integer getCapacidad() { return capacidad; }
    public void setCapacidad(Integer capacidad) { this.capacidad = capacidad; }
    public Integer getPrecioHora() { return precioHora; }
    public void setPrecioHora(Integer precioHora) { this.precioHora = precioHora; }
}