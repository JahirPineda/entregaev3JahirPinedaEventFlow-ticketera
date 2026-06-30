package cl.duoc.msvc_resenas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ResenasDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @Min(value = 1, message = "La puntuación mínima es 1")
    @Max(value = 5, message = "La puntuación máxima es 5")
    @Schema(description = "Puntuación de la reseña (1-5)", example = "5")
    private int puntuacion;

    @Size(max = 1000, message = "El comentario no puede superar los 1000 caracteres")
    @Schema(description = "Comentario de la reseña", example = "Excelente evento, muy bien organizado")
    private String comentario;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Fecha generada automáticamente")
    private LocalDateTime fecha;

    @Positive(message = "El ID de cliente debe ser válido")
    @Schema(description = "ID del cliente", example = "1")
    private int clienteId;

    @Positive(message = "El ID de evento debe ser válido")
    @Schema(description = "ID del evento", example = "1")
    private int eventoId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del cliente obtenido desde msvc-clientes")
    private String nombreCliente;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del evento obtenido desde msvc-eventos")
    private String nombreEvento;
}