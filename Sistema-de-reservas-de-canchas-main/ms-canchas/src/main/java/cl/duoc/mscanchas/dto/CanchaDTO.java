package cl.duoc.mscanchas.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CanchaDTO {
    
    private Long id;

    @NotBlank(message = "El nombre de la cancha es obligatorio")
    private String nombre;

    @NotBlank(message = "El tipo de pasto es obligatorio")
    private String tipoPasto;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1")
    private Integer capacidad;

    @NotNull(message = "El precio es obligatorio")
    @Min(value = 0, message = "El precio no puede ser negativo")
    private Double precioHora; 
}