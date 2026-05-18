package cl.duoc.msnotificaciones.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "notificaciones")
@Data
public class NotificacionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private String mensaje;
    private String tipo; // EMAIL o SMS
    private LocalDateTime fechaEnvio;
}
