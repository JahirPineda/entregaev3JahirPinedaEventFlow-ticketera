package cl.duoc.msvc_eventos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CategoriasDTO {
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
    private String descripcion;

    private String imagenUrl;
    private boolean activo;
}