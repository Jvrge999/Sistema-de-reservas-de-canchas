package cl.duoc.msautenticacion.service;

import cl.duoc.msautenticacion.dto.AuthDTO;

public interface AuthService {
    AuthDTO registrar(AuthDTO dto);
    AuthDTO login(AuthDTO dto);
}
