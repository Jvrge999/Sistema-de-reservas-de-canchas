package cl.duoc.msreportes.repository;

import cl.duoc.msreportes.model.ReporteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReporteRepository extends JpaRepository<ReporteEntity, Long> {
}
