package cl.duoc.msreservas.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "reservas")
@Data
public class ReservaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idCancha;
    private Long idUsuario;
    private LocalDate fecha;
    private LocalTime hora;
    private String estado; // Ej: "Confirmada", "Pendiente"
}