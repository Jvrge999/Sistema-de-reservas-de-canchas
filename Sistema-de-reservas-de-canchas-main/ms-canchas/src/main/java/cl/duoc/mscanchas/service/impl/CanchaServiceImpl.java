package cl.duoc.mscanchas.service.impl;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
import cl.duoc.mscanchas.service.CanchaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        // Regla de Negocio: Para formatos de fútbol 7 (14 jugadores), los partidos regionales exigen que el pasto sea Sintético.
        if (dto.getCapacidad() == 14 && !"Sintético".equalsIgnoreCase(dto.getTipoPasto())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: Las canchas con capacidad para 14 personas (Fútbol 7) deben ser de pasto Sintético para cumplir con el estándar regional.");
        }

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
        CanchaEntity entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Cancha no encontrada para actualizar"));

        if (dto.getCapacidad() == 14 && !"Sintético".equalsIgnoreCase(dto.getTipoPasto())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: Las canchas con capacidad para 14 personas (Fútbol 7) deben ser de pasto Sintético para cumplir con el estándar regional.");
        }

        entity.setNombre(dto.getNombre());
        entity.setTipoPasto(dto.getTipoPasto());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecioHora(dto.getPrecioHora());
        
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public void borrar(Long id) {
        CanchaEntity entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Cancha no encontrada para eliminar"));
            
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