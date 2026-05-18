package cl.duoc.msautenticacion.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "credenciales")
@Data
public class AuthEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
}
