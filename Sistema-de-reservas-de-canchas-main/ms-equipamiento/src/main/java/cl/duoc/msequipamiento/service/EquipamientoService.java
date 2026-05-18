package cl.duoc.msequipamiento.service;
import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import java.util.List;

public interface EquipamientoService {
    List<EquipamientoDTO> listarTodos();
    EquipamientoDTO guardar(EquipamientoDTO dto);
    void eliminar(Long id);
}
