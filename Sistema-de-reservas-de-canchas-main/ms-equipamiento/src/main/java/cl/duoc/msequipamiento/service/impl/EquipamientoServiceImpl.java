package cl.duoc.msequipamiento.service.impl;

import cl.duoc.msequipamiento.dto.EquipamientoDTO;
import cl.duoc.msequipamiento.model.EquipamientoEntity;
import cl.duoc.msequipamiento.repository.EquipamientoRepository;
import cl.duoc.msequipamiento.service.EquipamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        EquipamientoEntity entity;
        
        if (dto.getId() != null) {
            // Logica para Actualizar (PUT)
            entity = repo.findById(dto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Equipamiento no encontrado para actualizar"));
        } else {
            // Logica para Crear (POST)
            entity = new EquipamientoEntity();
        }

        // Regla de Negocio 1: El precio no puede ser negativo
        if (dto.getPrecioArriendo() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: El precio de arriendo no puede ser negativo");
        }

        entity.setNombre(dto.getNombre());
        entity.setTipo(dto.getTipo());
        entity.setPrecioArriendo(dto.getPrecioArriendo());
        entity.setDisponible(dto.getDisponible());
        
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public void eliminar(Long id) {
        EquipamientoEntity entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Equipamiento no encontrado para eliminar"));

        // Regla de Negocio 2: No se puede eliminar un equipamiento si está en uso (no disponible)
        if (!entity.getDisponible()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Regla de Negocio: No se puede eliminar un equipamiento que actualmente está en uso");
        }

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