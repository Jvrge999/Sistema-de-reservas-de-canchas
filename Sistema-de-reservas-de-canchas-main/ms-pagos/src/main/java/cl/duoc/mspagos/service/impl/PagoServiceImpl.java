package cl.duoc.mspagos.service.impl;

import cl.duoc.mspagos.client.ReservaClient;
import cl.duoc.mspagos.dto.PagoDTO;
import cl.duoc.mspagos.dto.ReservaDTO;
import cl.duoc.mspagos.model.PagoEntity;
import cl.duoc.mspagos.repository.PagoRepository;
import cl.duoc.mspagos.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        ReservaDTO reserva = reservaClient.obtenerReserva(dto.getIdReserva());
        if (reserva == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: No se puede procesar el pago porque la reserva no existe.");
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
        PagoEntity entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Pago no encontrado para actualizar"));
        
        entity.setIdReserva(dto.getIdReserva());
        entity.setMonto(dto.getMonto());
        entity.setMetodoPago(dto.getMetodoPago());
        entity.setEstado(dto.getEstado());
        entity = repo.save(entity);
        return convertirADTO(entity);
    }

    @Override
    public void borrar(Long id) {
        PagoEntity entity = repo.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Error: Pago no encontrado para eliminar"));

        // Regla de Negocio: No se puede eliminar un pago completado (Auditoría financiera)
        if ("COMPLETADO".equalsIgnoreCase(entity.getEstado())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Regla de Negocio: No se pueden eliminar pagos en estado COMPLETADO por motivos de auditoría.");
        }

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