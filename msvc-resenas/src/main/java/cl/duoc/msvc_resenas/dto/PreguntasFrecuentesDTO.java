package cl.duoc.msvc_resenas.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PreguntasFrecuentesDTO {
    private int id;

    @NotBlank(message = "La pregunta es obligatoria")
    @Size(max = 500, message = "La pregunta no puede superar los 500 caracteres")
    private String pregunta;

    @NotBlank(message = "La respuesta es obligatoria")
    @Size(max = 1000, message = "La respuesta no puede superar los 1000 caracteres")
    private String respuesta;

    private String categoria;

    @PositiveOrZero(message = "El orden no puede ser negativo")
    private int orden;

    private boolean activo;
}