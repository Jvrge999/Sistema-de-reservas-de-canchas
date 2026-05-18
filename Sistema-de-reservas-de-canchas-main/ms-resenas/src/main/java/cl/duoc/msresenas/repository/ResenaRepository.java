package cl.duoc.msresenas.repository;

import cl.duoc.msresenas.model.ResenaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResenaRepository extends JpaRepository<ResenaEntity, Long> {
}
