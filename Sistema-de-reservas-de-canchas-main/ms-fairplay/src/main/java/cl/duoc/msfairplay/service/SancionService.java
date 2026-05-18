package cl.duoc.msfairplay.service;

import cl.duoc.msfairplay.dto.SancionDTO;
import java.util.List;

public interface SancionService {
    SancionDTO registrarSancion(SancionDTO dto);
    List<SancionDTO> obtenerSancionesUsuario(Long idUsuario);
}
