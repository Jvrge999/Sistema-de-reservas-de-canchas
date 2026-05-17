package cl.duoc.msreservas.client;

import cl.duoc.msreservas.dto.UsuarioDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

// El nombre debe coincidir con el nombre del microservicio en el docker-compose
// La URL debe apuntar al contenedor de usuarios
@FeignClient(name = "ms-usuarios", url = "http://ms-usuarios:8083")
public interface UsuarioClient {

    @GetMapping("/usuarios/{id}")
    UsuarioDTO obtenerUsuario(@PathVariable("id") Long id);
}