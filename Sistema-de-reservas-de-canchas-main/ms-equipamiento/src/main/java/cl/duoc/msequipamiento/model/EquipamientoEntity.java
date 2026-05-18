package cl.duoc.msequipamiento.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "equipamiento")
@Data
public class EquipamientoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipo;
    private Double precioArriendo;
    private Boolean disponible;
}
