package cl.duoc.msreportes.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ReporteDTO {
    private Long id;
    private String titulo;
    private Double valorTotal;
    private LocalDateTime fechaGeneracion;
}
