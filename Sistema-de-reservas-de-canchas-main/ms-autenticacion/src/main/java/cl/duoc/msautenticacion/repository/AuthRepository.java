package cl.duoc.msautenticacion.repository;

import cl.duoc.msautenticacion.model.AuthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByEmail(String email);
}
