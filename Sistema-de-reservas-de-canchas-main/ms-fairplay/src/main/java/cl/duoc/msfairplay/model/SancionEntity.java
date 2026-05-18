package cl.duoc.msfairplay.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "sanciones")
@Data
public class SancionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idUsuario;
    private String motivo;
    private Boolean activo;
}
