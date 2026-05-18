package cl.duoc.msnotificaciones.service.impl;

import cl.duoc.msnotificaciones.dto.NotificacionDTO;
import cl.duoc.msnotificaciones.model.NotificacionEntity;
import cl.duoc.msnotificaciones.repository.NotificacionRepository;
import cl.duoc.msnotificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class NotificacionServiceImpl implements NotificacionService {

    @Autowired
    private NotificacionRepository repo;

    @Override
    public NotificacionDTO enviar(NotificacionDTO dto) {
        NotificacionEntity entity = new NotificacionEntity();
        entity.setIdUsuario(dto.getIdUsuario());
        entity.setMensaje(dto.getMensaje());
        entity.setTipo(dto.getTipo());
        entity.setFechaEnvio(LocalDateTime.now()); // Fecha automática
        
        entity = repo.save(entity);
        dto.setId(entity.getId());
        dto.setFechaEnvio(entity.getFechaEnvio());
        return dto;
    }
}
