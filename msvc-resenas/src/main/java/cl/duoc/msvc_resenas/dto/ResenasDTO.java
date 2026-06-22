package cl.duoc.msvc_resenas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResenasDTO {
    private int id;

    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    private int puntuacion;

    @Size(max = 1000, message = "El comentario no puede superar los 1000 caracteres")
    private String comentario;

    private LocalDateTime fecha;

    @Positive(message = "El ID de cliente debe ser válido")
    private int clienteId;

    @Positive(message = "El ID de evento debe ser válido")
    private int eventoId;

    private String nombreCliente;
    private String nombreEvento;
}