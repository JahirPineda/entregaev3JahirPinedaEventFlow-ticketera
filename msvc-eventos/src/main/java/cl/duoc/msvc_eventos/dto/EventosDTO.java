package cl.duoc.msvc_eventos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventosDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del evento", example = "Festival Rock 2024")
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    @Schema(description = "Descripción del evento", example = "Festival de música rock en vivo")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    @Schema(description = "Fecha del evento", example = "2024-12-01")
    private LocalDate fecha;

    @Schema(description = "Hora del evento", example = "20:00:00")
    private LocalTime hora;

    @Schema(description = "URL de la imagen del evento", example = "https://imagen.com/evento.jpg")
    private String imagenUrl;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado generado automáticamente")
    private boolean activo;

    @Positive(message = "El ID de categoría debe ser válido")
    @Schema(description = "ID de la categoría", example = "1")
    private int categoriaId;

    @Positive(message = "El ID de recinto debe ser válido")
    @Schema(description = "ID del recinto", example = "1")
    private int recintoId;
}