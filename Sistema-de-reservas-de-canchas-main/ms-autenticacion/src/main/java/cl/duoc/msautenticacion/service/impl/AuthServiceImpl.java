package cl.duoc.msautenticacion.service.impl;

import cl.duoc.msautenticacion.dto.AuthDTO;
import cl.duoc.msautenticacion.model.AuthEntity;
import cl.duoc.msautenticacion.repository.AuthRepository;
import cl.duoc.msautenticacion.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthRepository repo;

    @Override
    public AuthDTO registrar(AuthDTO dto) {
        AuthEntity entity = new AuthEntity();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        repo.save(entity);
        dto.setPassword("********"); // Ocultamos la pass en la respuesta
        dto.setToken("REGISTRO-EXITOSO");
        return dto;
    }

    @Override
    public AuthDTO login(AuthDTO dto) {
        Optional<AuthEntity> user = repo.findByEmail(dto.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(dto.getPassword())) {
            dto.setPassword("********");
            dto.setToken("TOKEN-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            return dto;
        }
        throw new RuntimeException("Credenciales inválidas");
    }
}
