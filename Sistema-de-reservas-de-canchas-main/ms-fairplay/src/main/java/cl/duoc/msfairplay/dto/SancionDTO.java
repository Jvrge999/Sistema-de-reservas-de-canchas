package cl.duoc.msfairplay.dto;

import lombok.Data;

@Data
public class SancionDTO {
    private Long id;
    private Long idUsuario;
    private String motivo;
    private Boolean activo;
}
