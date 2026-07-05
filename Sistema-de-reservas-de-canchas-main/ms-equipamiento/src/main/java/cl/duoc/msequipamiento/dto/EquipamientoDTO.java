package cl.duoc.msequipamiento.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EquipamientoDTO {
    
    private Long id;

    @NotBlank(message = "El nombre del equipamiento es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo de equipamiento es obligatorio")
    private String tipo;

    @NotNull(message = "El precio de arriendo es obligatorio")
    @Min(value = 0, message = "El precio de arriendo no puede ser negativo")
    private Double precioArriendo;

    @NotNull(message = "Debe indicar el estado de disponibilidad")
    private Boolean disponible;
}