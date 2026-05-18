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
import java.util.stream.Collectors;

@Service
public class PagoServiceImpl implements PagoService {

    @Autowired private PagoRepository repo;
    @Autowired private ReservaClient reservaClient;

    @Override
    public List<PagoDTO> listarTodos() {
        return repo.findAll().stream().map(this::convertirADTO).collect(Collectors.toList());
    }

    @Override
    public PagoDTO registrarPago(PagoDTO dto) {
        
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
