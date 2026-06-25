package cl.duoc.msvc_recintos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class RecintosDTO {
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La dirección es obligatoria")
    private String direccion;

    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;

    @Positive(message = "La capacidad debe ser mayor a 0")
    private int capacidad;

    private String imagenUrl;
    private boolean activo;
}