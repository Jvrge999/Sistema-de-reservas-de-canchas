package cl.duoc.msreportes.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Table(name = "reportes")
@Data
public class ReporteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Double valorTotal;
    private LocalDateTime fechaGeneracion;
}
