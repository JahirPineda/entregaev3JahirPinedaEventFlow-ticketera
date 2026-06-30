package cl.duoc.msvc_precios.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PreciosDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "El tipo de entrada es obligatorio")
    @Schema(description = "Tipo de entrada", example = "VIP")
    private String tipoEntrada;

    @Positive(message = "El valor debe ser mayor a 0")
    @Schema(description = "Valor del precio", example = "50000")
    private double valor;

    @PositiveOrZero(message = "El stock no puede ser negativo")
    @Schema(description = "Stock disponible", example = "100")
    private int stock;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado generado automáticamente")
    private boolean activo;

    @Positive(message = "El ID de evento debe ser válido")
    @Schema(description = "ID del evento", example = "1")
    private int eventoId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del evento obtenido desde msvc-eventos")
    private String nombreEvento;
}