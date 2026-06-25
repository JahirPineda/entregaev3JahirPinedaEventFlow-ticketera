package cl.duoc.msvc_ordenes.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MisOrdenesDTO {
    private int id;
    private LocalDateTime fechaCompra;

    @Positive(message = "El total debe ser mayor a 0")
    private double total;

    private String estado;

    @NotBlank(message = "El método de pago es obligatorio")
    private String metodoPago;

    private String nombreCliente;
    private String nombreEvento;
}