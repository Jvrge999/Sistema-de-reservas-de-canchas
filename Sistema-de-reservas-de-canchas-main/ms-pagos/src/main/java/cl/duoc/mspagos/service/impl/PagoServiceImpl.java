package cl.duoc.mspagos.service.impl;

import cl.duoc.mspagos.client.ReservaClient;
import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.dto.ReservaDTO;
import cl.duoc.mspagos.model.PagoEntity;
import cl.duoc.mspagos.repository.PagoRepository;
import cl.duoc.mspagos.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired 
    private PagoRepository repo;
    
    @Autowired 
    private ReservaClient reservaClient;

    @Override
    public List<PagoDTO> listarTodos() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public PagoDTO registrarPago(PagoDTO dto) {
        // Validación con Feign Client que ya tenías (¡Excelente práctica!)
        ReservaDTO reserva = reservaClient.obtenerReserva(dto.getIdReserva());
        if (reserva == null) {
            throw new RuntimeException("No se puede procesar el pago: La reserva no existe.");
        }
        
        PagoEntity entity = new PagoEntity();
        entity.setIdReserva(dto.getIdReserva());
        entity.setMonto(dto.getMonto());
        entity.setMetodoPago(dto.getMetodoPago());
        entity.setEstado("COMPLETADO");
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public PagoDTO actualizar(Long id, PagoDTO dto) {
        Optional<PagoEntity> existente = repo.findById(id);
        
        if (existente.isPresent()) {
            PagoEntity entity = existente.get();
            entity.setIdReserva(dto.getIdReserva());
            entity.setMonto(dto.getMonto());
            entity.setMetodoPago(dto.getMetodoPago());
            entity.setEstado(dto.getEstado());
            entity = repo.save(entity);
            return convertirADTO(entity);
        }
        throw new RuntimeException("Pago no encontrado");
    }

    @Override
    public void borrar(Long id) {
        repo.deleteById(id);
    }

    private PagoDTO convertirADTO(PagoEntity entity) {
        PagoDTO dto = new PagoDTO();
        dto.setId(entity.getId());
        dto.setIdReserva(entity.getIdReserva());
        dto.setMonto(entity.getMonto());
        dto.setMetodoPago(entity.getMetodoPago());
        dto.setEstado(entity.getEstado());
        return dto;
    }
}