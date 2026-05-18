package cl.duoc.msresenas.service;

import cl.duoc.msresenas.dto.ResenaDTO;
import java.util.List;

public interface ResenaService {
    ResenaDTO crear(ResenaDTO dto);
    List<ResenaDTO> listar();
}
