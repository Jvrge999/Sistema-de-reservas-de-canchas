package cl.duoc.mspagos.dto;
import lombok.Data;

@Data
public class ReservaDTO {
    private Long id;
    private Long idUsuario;
    private Long idCancha;
    private String fecha;
}
