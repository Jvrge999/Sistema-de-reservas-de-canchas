package cl.duoc.msfairplay.service;

import cl.duoc.msfairplay.dto.SancionDTO;
import java.util.List;

public interface SancionService {
    List<SancionDTO> listarTodas();
    List<SancionDTO> obtenerSancionesUsuario(Long idUsuario);
    SancionDTO registrarSancion(SancionDTO dto);
    SancionDTO actualizar(Long id, SancionDTO dto);
    void borrar(Long id);
}