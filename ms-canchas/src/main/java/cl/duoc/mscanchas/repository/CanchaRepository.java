package cl.duoc.mscanchas.repository;

import cl.duoc.mscanchas.model.CanchaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CanchaRepository extends JpaRepository<CanchaEntity, Long> {
}