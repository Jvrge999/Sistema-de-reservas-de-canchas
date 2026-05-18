package cl.duoc.msequipamiento.service.impl;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.model.EquipamientoEntity;
import cl.duoc.msequipamiento.repository.EquipamientoRepository;
import cl.duoc.msequipamiento.service.EquipamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipamientoServiceImpl implements EquipamientoService {

    @Autowired
    private EquipamientoRepository repo;

    @Override
    public List<EquipamientoDTO> listarTodos() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public EquipamientoDTO guardar(EquipamientoDTO dto) {
        EquipamientoEntity entity = new EquipamientoEntity();
        entity.setNombre(dto.getNombre());
        entity.setTipo(dto.getTipo());
        entity.setPrecioArriendo(dto.getPrecioArriendo());
        entity.setDisponible(dto.getDisponible());
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public void eliminar(Long id) {
        repo.deleteById(id);
    }

    private EquipamientoDTO convertirADTO(EquipamientoEntity entity) {
        EquipamientoDTO dto = new EquipamientoDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setTipo(entity.getTipo());
        dto.setPrecioArriendo(entity.getPrecioArriendo());
        dto.setDisponible(entity.getDisponible());
        return dto;
    }
}
