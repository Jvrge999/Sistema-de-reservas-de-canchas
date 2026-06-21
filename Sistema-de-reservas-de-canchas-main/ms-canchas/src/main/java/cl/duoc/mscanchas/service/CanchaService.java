package cl.duoc.mscanchas.service;

import cl.duoc.mscanchas.dto.CanchaDTO;
import java.util.List;

public interface CanchaService {
    List<CanchaDTO> listarTodas();
    CanchaDTO guardar(CanchaDTO dto);
    CanchaDTO actualizar(Long id, CanchaDTO dto);
    void borrar(Long id);
}