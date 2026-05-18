package cl.duoc.msnotificaciones.controller;

import cl.duoc.msnotificaciones.dto.NotificacionDTO;
import cl.duoc.msnotificaciones.service.NotificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificaciones")
public class NotificacionController {

    @Autowired
    private NotificacionService service;

    @PostMapping("/enviar")
    public NotificacionDTO enviarNotificacion(@RequestBody NotificacionDTO dto) {
        return service.enviar(dto);
    }
}
