package cl.duoc.msfairplay.service.impl;

import cl.duoc.msfairplay.dto.SancionDTO;
import cl.duoc.msfairplay.model.SancionEntity;
import cl.duoc.msfairplay.repository.SancionRepository;
import cl.duoc.msfairplay.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    private SancionRepository repo;

    @Override
    public SancionDTO registrarSancion(SancionDTO dto) {
        SancionEntity entity = new SancionEntity();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setMotivo(dto.getMotivo());
        entity.setActivo(true); // Se activa por defecto al registrar
        entity = repo.save(entity);
        
        dto.setId(entity.getId());
        dto.setActivo(entity.getActivo());
        return dto;
    }

    @Override
    public List<SancionDTO> obtenerSancionesUsuario(Long idUsuario) {
        return repo.findByIdUsuario(idUsuario).stream().map(entity -> {
            SancionDTO dto = new SancionDTO();
            dto.setId(entity.getId());
            dto.setIdUsuario(entity.getIdUsuario());
            dto.setMotivo(entity.getMotivo());
            dto.setActivo(entity.getActivo());
            return dto;
        }).collect(Collectors.toList());
    }
}
