package cl.duoc.msfairplay.repository;

import cl.duoc.msfairplay.model.SancionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SancionRepository extends JpaRepository<SancionEntity, Long> {
    List<SancionEntity> findByIdUsuario(Long idUsuario);
}
