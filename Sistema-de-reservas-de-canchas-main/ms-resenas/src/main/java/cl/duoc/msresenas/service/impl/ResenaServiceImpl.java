package cl.duoc.msresenas.service.impl;

import cl.duoc.msresenas.dto.ResenaDTO;
import cl.duoc.msresenas.model.ResenaEntity;
import cl.duoc.msresenas.repository.ResenaRepository;
import cl.duoc.msresenas.service.ResenaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenaServiceImpl implements ResenaService {
    @Autowired private ResenaRepository repo;

    @Override
    public ResenaDTO crear(ResenaDTO dto) {
        ResenaEntity entity = new ResenaEntity();
        entity.setIdCancha(dto.getIdCancha());
        entity.setComentario(dto.getComentario());
        entity.setEstrellas(dto.getEstrellas());
        entity = repo.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    @Override
    public List<ResenaDTO> listar() {
        return repo.findAll().stream().map(e -> {
            ResenaDTO d = new ResenaDTO();
            d.setId(e.getId());
            d.setIdCancha(e.getIdCancha());
            d.setComentario(e.getComentario());
            d.setEstrellas(e.getEstrellas());
            return d;
        }).collect(Collectors.toList());
    }
}
