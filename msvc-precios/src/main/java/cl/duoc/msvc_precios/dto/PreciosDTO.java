package cl.duoc.msvc_precios.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PreciosDTO {
    private int id;

    @NotBlank(message = "El tipo de entrada es obligatorio")
    private String tipoEntrada;

    @Positive(message = "El valor debe ser mayor a 0")
    private double valor;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    private int stock;

    private boolean activo;

    @Positive(message = "El ID de evento debe ser válido")
    private int eventoId;

    private String nombreEvento;
}