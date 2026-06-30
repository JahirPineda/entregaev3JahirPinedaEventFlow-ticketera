package cl.duoc.msvc_eventos.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriasDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    @Schema(description = "Nombre de la categoría", example = "Música")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    @Schema(description = "Descripción de la categoría", example = "Eventos musicales de todo género")
    private String descripcion;

    @Schema(description = "URL de la imagen de la categoría", example = "https://imagen.com/categoria.jpg")
    private String imagenUrl;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado generado automáticamente")
    private boolean activo;
}