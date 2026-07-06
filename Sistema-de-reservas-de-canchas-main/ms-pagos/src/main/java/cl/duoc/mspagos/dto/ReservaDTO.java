package cl.duoc.mspagos.dto;

import lombok.Data;

@Data
public class ReservaDTO {
    private Long id;
    private Long idCancha;
    private Long idUsuario;
    private String fecha;
    private String hora;
    private String estado;
}