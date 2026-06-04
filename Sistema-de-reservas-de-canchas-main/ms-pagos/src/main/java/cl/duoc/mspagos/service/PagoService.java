package cl.duoc.mspagos.service;

import cl.duoc.mspagos.dto.PagoDTO;
import java.util.List;

public interface PagoService {
    List<PagoDTO> listarTodos();
    PagoDTO registrarPago(PagoDTO dto);
    PagoDTO actualizar(Long id, PagoDTO dto);
    void borrar(Long id);
}