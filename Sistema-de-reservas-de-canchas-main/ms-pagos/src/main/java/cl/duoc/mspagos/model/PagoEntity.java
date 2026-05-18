package cl.duoc.mspagos.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "pagos")
@Data
public class PagoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idReserva;
    private Double monto;
    private String metodoPago;
    private String estado;
}
