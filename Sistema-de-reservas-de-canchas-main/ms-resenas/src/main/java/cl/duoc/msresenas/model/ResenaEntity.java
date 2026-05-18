package cl.duoc.msresenas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "resenas")
@Data
public class ResenaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idCancha;
    private String comentario;
    private Integer estrellas; // 1 a 5
}
