package cl.duoc.msequipamiento.repository;

import cl.duoc.msequipamiento.model.EquipamientoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipamientoRepository extends JpaRepository<EquipamientoEntity, Long> {
}