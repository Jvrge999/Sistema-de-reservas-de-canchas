package cl.duoc.msnotificaciones.service;

import cl.duoc.msnotificaciones.dto.NotificacionDTO;

public interface NotificacionService {
    NotificacionDTO enviar(NotificacionDTO dto);
}
