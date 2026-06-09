package cl.duoc.mspagos.client;

import cl.duoc.mspagos.dto.ReservaDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-reservas", url = "http://ms-reservas:8084")
public interface ReservaClient {
    @GetMapping("/reservas/{id}")
    ReservaDTO obtenerReserva(@PathVariable("id") Long id);
}