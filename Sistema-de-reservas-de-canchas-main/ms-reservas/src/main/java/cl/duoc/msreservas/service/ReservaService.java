package cl.duoc.msreservas.service;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository repo;

    public List<ReservaEntity> listarTodas() {
        return repo.findAll();
    }

    // --- ESTE ES EL MÉTODO QUE FALTABA ---
    public ReservaEntity obtenerPorId(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    public ReservaEntity guardar(ReservaDTO dto) {
        ReservaEntity entity = new ReservaEntity();
        entity.setIdCancha(dto.getIdCancha());
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setEstado(dto.getEstado());
        return repo.save(entity);
    }

    public ReservaEntity actualizar(Long id, ReservaDTO dto) {
        Optional<ReservaEntity> existente = repo.findById(id);
        
        if (existente.isPresent()) {
            ReservaEntity entity = existente.get();
            entity.setIdCancha(dto.getIdCancha());
            entity.setIdUsuario(dto.getIdUsuario());
            entity.setFecha(dto.getFecha());
            entity.setHora(dto.getHora());
            entity.setEstado(dto.getEstado());
            return repo.save(entity);
        }
        throw new RuntimeException("Reserva no encontrada");
    }

    public void borrar(Long id) {
        repo.deleteById(id);
    }
}