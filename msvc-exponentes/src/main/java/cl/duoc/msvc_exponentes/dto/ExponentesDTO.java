package cl.duoc.msvc_exponentes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class ExponentesDTO {
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Size(max = 1000, message = "La biografía no puede superar los 1000 caracteres")
    private String biografia;

    private String nacionalidad;
    private String imagenUrl;
    private String redesSociales;

    @Positive(message = "El ID de evento debe ser válido")
    private int eventoId;

    private String nombreEvento;
}