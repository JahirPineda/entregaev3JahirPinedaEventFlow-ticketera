package cl.duoc.msvc_ordenes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MisOrdenesDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Fecha de compra generada automáticamente")
    private LocalDateTime fechaCompra;

    @Positive(message = "El total debe ser mayor a 0")
    @Schema(description = "Total de la orden", example = "25000")
    private double total;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado generado automáticamente")
    private String estado;

    @NotBlank(message = "El método de pago es obligatorio")
    @Schema(description = "Método de pago", example = "TARJETA")
    private String metodoPago;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del cliente obtenido desde msvc-clientes")
    private String nombreCliente;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del evento obtenido desde msvc-eventos")
    private String nombreEvento;

    @Schema(description = "ID del cliente", example = "1")
    private int clienteId;

    @Schema(description = "ID del evento", example = "1")
    private int eventoId;
}