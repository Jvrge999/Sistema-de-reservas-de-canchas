package cl.duoc.msfairplay.service.impl;

import cl.duoc.msfairplay.dto.SancionDTO;
import cl.duoc.msfairplay.model.SancionEntity;
import cl.duoc.msfairplay.repository.SancionRepository;
import cl.duoc.msfairplay.service.SancionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SancionServiceImpl implements SancionService {

    @Autowired
    private SancionRepository repo;

    @Override
    public List<SancionDTO> listarTodas() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public SancionDTO registrarSancion(SancionDTO dto) {
        SancionEntity entity = new SancionEntity();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setMotivo(dto.getMotivo());
        entity.setActivo(true); // Se mantiene tu logica de activar por defecto al registrar
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public List<SancionDTO> obtenerSancionesUsuario(Long idUsuario) {
        // Se mantiene la llamada a tu metodo personalizado del repositorio
        return repo.findByIdUsuario(idUsuario).stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public SancionDTO actualizar(Long id, SancionDTO dto) {
        Optional<SancionEntity> existente = repo.findById(id);
        
        if (existente.isPresent()) {
            SancionEntity entity = existente.get();
            entity.setIdUsuario(dto.getIdUsuario());
            entity.setMotivo(dto.getMotivo());
            // Aqui permitimos que se cambie el estado (ej. para desactivar una sancion)
            entity.setActivo(dto.getActivo());
            entity = repo.save(entity);
            return convertirADTO(entity);
        }
        throw new RuntimeException("Sancion no encontrada");
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    // Metodo auxiliar para mantener el codigo limpio
    private SancionDTO convertirADTO(SancionEntity entity) {
        SancionDTO dto = new SancionDTO();
        dto.setId(entity.getId());
        dto.setIdUsuario(entity.getIdUsuario());
        dto.setMotivo(entity.getMotivo());
        dto.setActivo(entity.getActivo());
        return dto;
    }
}