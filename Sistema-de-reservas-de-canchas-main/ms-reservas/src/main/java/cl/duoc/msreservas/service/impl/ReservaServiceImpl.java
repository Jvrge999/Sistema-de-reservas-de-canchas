package cl.duoc.msreservas.service.impl;

import cl.duoc.msreservas.dto.ReservaDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import cl.duoc.msreservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Reserva no encontrada"));
        return convertirADTO(entity);
    }

    @Override
    public ReservaDTO guardar(ReservaDTO dto) {
        // Regla de Negocio: No se puede hacer una reserva para una fecha en el pasado
        if (dto.getFecha().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: No se puede agendar una reserva en una fecha pasada.");
        }

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
        ReservaEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Reserva no encontrada para actualizar"));

        if (dto.getFecha().isBefore(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Regla de Negocio: No se puede actualizar una reserva a una fecha pasada.");
        }

        entity.setIdCancha(dto.getIdCancha());
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setFecha(dto.getFecha());
        entity.setHora(dto.getHora());
        entity.setEstado(dto.getEstado());
        return convertirADTO(repo.save(entity));
    }

    @Override
    public void borrar(Long id) {
        ReservaEntity entity = repo.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Reserva no encontrada para eliminar"));

        // Regla de Negocio: No se puede eliminar una reserva ya confirmada
        if ("CONFIRMADA".equalsIgnoreCase(entity.getEstado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Regla de Negocio: No se puede eliminar una reserva que ya está CONFIRMADA.");
        }

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