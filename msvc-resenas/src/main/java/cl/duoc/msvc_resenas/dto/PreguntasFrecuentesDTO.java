package cl.duoc.msvc_resenas.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PreguntasFrecuentesDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "La pregunta es obligatoria")
    @Size(max = 500, message = "La pregunta no puede superar los 500 caracteres")
    @Schema(description = "Pregunta frecuente", example = "¿Cómo puedo comprar entradas?")
    private String pregunta;

    @NotBlank(message = "La respuesta es obligatoria")
    @Size(max = 1000, message = "La respuesta no puede superar los 1000 caracteres")
    @Schema(description = "Respuesta a la pregunta", example = "Puedes comprar entradas a través de nuestra plataforma web")
    private String respuesta;

    @Schema(description = "Categoría de la pregunta", example = "Pagos")
    private String categoria;

    @PositiveOrZero(message = "El orden no puede ser negativo")
    @Schema(description = "Orden de visualización", example = "1")
    private int orden;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado generado automáticamente")
    private boolean activo;
}