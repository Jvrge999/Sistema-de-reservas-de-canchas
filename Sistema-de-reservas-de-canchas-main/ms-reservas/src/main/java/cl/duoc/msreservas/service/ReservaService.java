package cl.duoc.msreservas.service;

import cl.duoc.msreservas.dto.ReservaDTO;
import java.util.List;

public interface ReservaService {
    List<ReservaDTO> listarTodas();
    ReservaDTO obtenerPorId(Long id);
    ReservaDTO guardar(ReservaDTO dto);
    ReservaDTO actualizar(Long id, ReservaDTO dto);
    void borrar(Long id);
}