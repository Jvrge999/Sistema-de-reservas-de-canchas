package cl.duoc.msnotificaciones.repository;

import cl.duoc.msnotificaciones.model.NotificacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificacionRepository extends JpaRepository<NotificacionEntity, Long> {
}
