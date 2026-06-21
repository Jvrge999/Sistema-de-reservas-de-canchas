package cl.duoc.mscanchas.service.impl;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
import cl.duoc.mscanchas.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CanchaServiceImpl implements CanchaService {

    @Autowired
    private CanchaRepository repo;

    @Override
    public List<CanchaDTO> listarTodas() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public CanchaDTO guardar(CanchaDTO dto) {
        CanchaEntity entity = new CanchaEntity();
        entity.setNombre(dto.getNombre());
        entity.setTipoPasto(dto.getTipoPasto());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecioHora(dto.getPrecioHora());
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public CanchaDTO actualizar(Long id, CanchaDTO dto) {
        Optional<CanchaEntity> existente = repo.findById(id);
        if (existente.isPresent()) {
            CanchaEntity entity = existente.get();
            entity.setNombre(dto.getNombre());
            entity.setTipoPasto(dto.getTipoPasto());
            entity.setCapacidad(dto.getCapacidad());
            entity.setPrecioHora(dto.getPrecioHora());
            entity = repo.save(entity);
            return convertirADTO(entity);
        }
        throw new RuntimeException("Cancha no encontrada");
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    private CanchaDTO convertirADTO(CanchaEntity entity) {
        CanchaDTO dto = new CanchaDTO();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setTipoPasto(entity.getTipoPasto());
        dto.setCapacidad(entity.getCapacidad());
        dto.setPrecioHora(entity.getPrecioHora());
        return dto;
    }
}