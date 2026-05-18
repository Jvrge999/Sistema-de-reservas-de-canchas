package cl.duoc.msnotificaciones.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class NotificacionDTO {
    private Long id;
    private Long idUsuario;
    private String mensaje;
    private String tipo;
    private LocalDateTime fechaEnvio;
}
