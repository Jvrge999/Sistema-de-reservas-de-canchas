package cl.duoc.msreportes.service;

import cl.duoc.msreportes.dto.ReporteDTO;
import java.util.List;

public interface ReporteService {
    ReporteDTO generarReporte(ReporteDTO dto);
    List<ReporteDTO> listarHistorial();
}
