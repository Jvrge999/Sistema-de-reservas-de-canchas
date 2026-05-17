package cl.duoc.msusuarios.model;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name = "usuarios") @Data
public class UsuarioEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;
}