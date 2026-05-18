package cl.duoc.msreservas.controller;

import cl.duoc.msreservas.client.UsuarioClient;
import cl.duoc.msreservas.dto.UsuarioDTO;
import cl.duoc.msreservas.model.ReservaEntity;
import cl.duoc.msreservas.repository.ReservaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    private static final Logger log = LoggerFactory.getLogger(ReservaController.class);

    @Autowired
    private ReservaRepository repo;

    @Autowired
    private UsuarioClient usuarioClient;

    @GetMapping("/test-feign/{idUsuario}")
    public UsuarioDTO testFeign(@PathVariable Long idUsuario) {
        log.info("Iniciando consulta Feign para ID: {}", idUsuario);
        return usuarioClient.obtenerUsuario(idUsuario);
    }

    @GetMapping
    public List<ReservaEntity> listar() {
        return repo.findAll();
    }

    // ¡AQUÍ ESTÁ LA PUERTA PARA MS-PAGOS!
    @GetMapping("/{id}")
    public Optional<ReservaEntity> obtenerPorId(@PathVariable Long id) {
        return repo.findById(id);
    }

    @PostMapping
    public ReservaEntity guardar(@RequestBody ReservaEntity reserva) {
        return repo.save(reserva);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}
