package cl.duoc.msequipamiento.dto;
import lombok.Data;

@Data
public class EquipamientoDTO {
    private Long id;
    private String nombre;
    private String tipo; // ej: Raqueta, Balón
    private Double precioArriendo;
    private Boolean disponible;
}
