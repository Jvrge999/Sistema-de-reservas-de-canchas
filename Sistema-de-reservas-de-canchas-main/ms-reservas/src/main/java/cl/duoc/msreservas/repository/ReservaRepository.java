package cl.duoc.msreservas.repository;

import cl.duoc.msreservas.model.ReservaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<ReservaEntity, Long> {
}