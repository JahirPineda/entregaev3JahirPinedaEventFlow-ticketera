package cl.duoc.msvc_tickets.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TicketsGeneradosDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Código QR generado automáticamente")
    private String codigoQR;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Fecha de emisión generada automáticamente")
    private LocalDateTime fechaEmision;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado de uso generado automáticamente")
    private boolean usado;

    @NotBlank(message = "El tipo de entrada es obligatorio")
    @Schema(description = "Tipo de entrada", example = "VIP")
    private String tipoEntrada;

    @Schema(description = "ID de la orden", example = "1")
    private int ordenId;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del cliente obtenido desde msvc-clientes")
    private String nombreCliente;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Nombre del evento obtenido desde msvc-eventos")
    private String nombreEvento;

    @Positive(message = "El ID de cliente debe ser válido")
    @Schema(description = "ID del cliente", example = "1")
    private int clienteId;

    @Positive(message = "El ID de evento debe ser válido")
    @Schema(description = "ID del evento", example = "1")
    private int eventoId;
}