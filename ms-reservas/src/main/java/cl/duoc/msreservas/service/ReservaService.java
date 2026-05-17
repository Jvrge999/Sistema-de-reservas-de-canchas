package cl.duoc.msreservas.service;

import cl.duoc.msreservas.client.UsuarioClient;
import cl.duoc.msreservas.dto.UsuarioDTO;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservaService {

    private static final Logger log = LoggerFactory.getLogger(ReservaService.class);

    @Autowired
    private UsuarioClient usuarioClient;

    public UsuarioDTO validarUsuarioRemoto(Long idUsuario) {
        log.info("Iniciando llamada Feign a ms-usuarios para buscar ID: {}", idUsuario);
        
        try {
            UsuarioDTO usuario = usuarioClient.obtenerUsuarioPorId(idUsuario);
            log.info("Comunicación Feign exitosa. Usuario recibido: {}", usuario.getNombre());
            return usuario;
            
        } catch (FeignException.NotFound e) {
            log.warn("Fallo Feign controlado (404): Usuario ID {} no existe en db-usuarios.", idUsuario);
            throw new RuntimeException("El usuario ingresado no existe en el sistema.");
            
        } catch (FeignException e) {
            log.error("Fallo Feign crítico (500/503): Error de red o servicio ms-usuarios caído.");
            throw new RuntimeException("Servicio de usuarios temporalmente no disponible.");
        }
    }
}