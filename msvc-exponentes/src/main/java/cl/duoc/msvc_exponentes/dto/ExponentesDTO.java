package cl.duoc.msvc_exponentes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ExponentesDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Nombre del exponente", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(description = "Apellido del exponente", example = "González")
    private String apellido;

    @Size(max = 1000, message = "La biografía no puede superar los 1000 caracteres")
    @Schema(description = "Biografía del exponente", example = "Artista chileno con más de 10 años de trayectoria")
    private String biografia;

    @Schema(description = "Nacionalidad del exponente", example = "Chilena")
    private String nacionalidad;

    @Schema(description = "URL de la imagen del exponente", example = "https://imagen.com/exponente.jpg")
    private String imagenUrl;

    @Schema(description = "Redes sociales del exponente", example = "@juangonzalez")
    private String redesSociales;

    @Positive(message = "El ID de evento debe ser válido")
    @Schema(description = "ID del evento", example = "1")
    private int eventoId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del evento obtenido desde msvc-eventos")
    private String nombreEvento;
}