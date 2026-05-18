package cl.duoc.mscanchas.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "canchas")
@Data
public class CanchaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String tipoPasto; // Sintético o Natural
    private int capacidad;
    private double precioHora;
}