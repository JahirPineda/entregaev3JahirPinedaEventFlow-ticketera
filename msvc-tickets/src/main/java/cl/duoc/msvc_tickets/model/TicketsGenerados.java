package cl.duoc.msvc_tickets.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tickets_generados")
public class TicketsGenerados {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "codigo_qr", unique = true)
    private String codigoQR;

    @Column(name = "fecha_emision")
    private LocalDateTime fechaEmision;

    @Column(nullable = false)
    private boolean usado;

    @Column(name = "tipo_entrada")
    private String tipoEntrada;

    @Column(name = "orden_id")
    private int ordenId;

    @Column(name = "cliente_id")
    private int clienteId;

    @Column(name = "evento_id")
    private int eventoId;
}