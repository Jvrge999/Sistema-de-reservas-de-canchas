package cl.duoc.mspagos.controller;

import cl.duoc.mspagos.model.PagoEntity;
import cl.duoc.mspagos.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoRepository repo;

    @GetMapping
    public List<PagoEntity> listar() {
        return repo.findAll();
    }

    @PostMapping
    public PagoEntity guardar(@RequestBody PagoEntity pago) {
        return repo.save(pago);
    }

    @DeleteMapping("/{id}")
    public void borrar(@PathVariable Long id) {
        repo.deleteById(id);
    }
}