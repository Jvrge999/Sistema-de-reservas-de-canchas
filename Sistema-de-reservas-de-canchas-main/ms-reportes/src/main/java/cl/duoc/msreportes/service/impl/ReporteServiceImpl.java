package cl.duoc.msreportes.service.impl;

import cl.duoc.msreportes.dto.ReporteDTO;
import cl.duoc.msreportes.model.ReporteEntity;
import cl.duoc.msreportes.repository.ReporteRepository;
import cl.duoc.msreportes.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReporteServiceImpl implements ReporteService {
    @Autowired private ReporteRepository repo;

    @Override
    public ReporteDTO generarReporte(ReporteDTO dto) {
        ReporteEntity entity = new ReporteEntity();
        entity.setTitulo(dto.getTitulo());
        entity.setValorTotal(dto.getValorTotal());
        entity.setFechaGeneracion(LocalDateTime.now());
        entity = repo.save(entity);
        dto.setId(entity.getId());
        dto.setFechaGeneracion(entity.getFechaGeneracion());
        return dto;
    }

    @Override
    public List<ReporteDTO> listarHistorial() {
        return repo.findAll().stream().map(e -> {
            ReporteDTO d = new ReporteDTO();
            d.setId(e.getId());
            d.setTitulo(e.getTitulo());
            d.setValorTotal(e.getValorTotal());
            d.setFechaGeneracion(e.getFechaGeneracion());
            return d;
        }).collect(Collectors.toList());
    }
}
