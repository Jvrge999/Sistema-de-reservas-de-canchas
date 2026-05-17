package cl.duoc.mspagos.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "pagos")
@Data
public class PagoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idReserva; // Se conecta lógicamente con el MS de Reservas
    private Double monto;
    private String metodoPago; // Ej: "Transferencia", "Crédito", "Débito"
    private LocalDateTime fechaPago;
}
