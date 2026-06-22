package cl.duoc.msvc_tickets.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class MisOrdenesDTO {
    private int id;
    private LocalDateTime fechaCompra;
    private double total;
    private String estado;
    private String metodoPago;
    private int clienteId;
    private int eventoId;
}