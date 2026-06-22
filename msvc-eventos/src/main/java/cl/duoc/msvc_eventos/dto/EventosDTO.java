package cl.duoc.msvc_eventos.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventosDTO {
    private int id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Size(max = 1000, message = "La descripción no puede superar los 1000 caracteres")
    private String descripcion;

    @NotNull(message = "La fecha es obligatoria")
    private LocalDate fecha;

    private LocalTime hora;
    private String imagenUrl;
    private boolean activo;

    @Positive(message = "El ID de categoría debe ser válido")
    private int categoriaId;

    @Positive(message = "El ID de recinto debe ser válido")
    private int recintoId;
}