package cl.duoc.msresenas.dto;

import lombok.Data;

@Data
public class ResenaDTO {
    private Long id;
    private Long idCancha;
    private String comentario;
    private Integer estrellas;
}
