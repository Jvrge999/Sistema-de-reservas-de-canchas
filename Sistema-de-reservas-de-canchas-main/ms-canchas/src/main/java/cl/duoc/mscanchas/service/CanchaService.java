package cl.duoc.mscanchas.service;

import cl.duoc.mscanchas.dto.CanchaDTO;
import cl.duoc.mscanchas.model.CanchaEntity;
import cl.duoc.mscanchas.repository.CanchaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CanchaService {

    @Autowired
    private CanchaRepository repo;

    public List<CanchaEntity> listarTodas() {
        return repo.findAll();
    }

    public CanchaEntity guardar(CanchaDTO dto) {
        CanchaEntity entity = new CanchaEntity();
        entity.setNombre(dto.getNombre());
        entity.setTipoPasto(dto.getTipoPasto());
        entity.setCapacidad(dto.getCapacidad());
        entity.setPrecioHora(dto.getPrecioHora());
        return repo.save(entity);
    }

    public CanchaEntity actualizar(Long id, CanchaDTO dto) {
        Optional<CanchaEntity> canchaExistente = repo.findById(id);
        
        if (canchaExistente.isPresent()) {
            CanchaEntity entity = canchaExistente.get();
            entity.setNombre(dto.getNombre());
            entity.setTipoPasto(dto.getTipoPasto());
            entity.setCapacidad(dto.getCapacidad());
            entity.setPrecioHora(dto.getPrecioHora());
            return repo.save(entity);
        }
        throw new RuntimeException("Cancha no encontrada");
    }

    public void borrar(Long id) {
        repo.deleteById(id);
    }
}