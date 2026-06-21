package cl.duoc.msreservas.service.impl;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import cl.duoc.msreservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository repo;

    @Override
    public List<ReservaDTO> listarTodas() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public ReservaDTO obtenerPorId(Long id) {
        ReservaEntity entity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
        return convertirADTO(entity);
    }

    @Override
    public ReservaDTO guardar(ReservaDTO dto) {
        ReservaEntity entity = new ReservaEntity();
        entity.setIdCancha(dto.getIdCancha());
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setEstado(dto.getEstado());
        return convertirADTO(repo.save(entity));
    }

    @Override
    public ReservaDTO actualizar(Long id, ReservaDTO dto) {
        Optional<ReservaEntity> existente = repo.findById(id);
        if (existente.isPresent()) {
            ReservaEntity entity = existente.get();
            entity.setIdCancha(dto.getIdCancha());
            entity.setIdUsuario(dto.getIdUsuario());
            entity.setFecha(dto.getFecha());
            entity.setHora(dto.getHora());
            entity.setEstado(dto.getEstado());
            return convertirADTO(repo.save(entity));
        }
        throw new RuntimeException("Reserva no encontrada");
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    private ReservaDTO convertirADTO(ReservaEntity entity) {
        ReservaDTO dto = new ReservaDTO();
        dto.setId(entity.getId());
        dto.setIdCancha(entity.getIdCancha());
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setFecha(entity.getFecha());
        dto.setHora(entity.getHora());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}