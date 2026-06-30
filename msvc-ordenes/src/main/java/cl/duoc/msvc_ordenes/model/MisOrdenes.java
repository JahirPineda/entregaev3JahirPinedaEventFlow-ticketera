package cl.duoc.msvc_ordenes.model;

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
@Table(name = "mis_ordenes")
public class MisOrdenes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fecha_compra")
    private LocalDateTime fechaCompra;

    @Column(nullable = false)
    private double total;

    @Column(nullable = false)
    private String estado;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "cliente_id")
    private int clienteId;

    @Column(name = "evento_id")
    private int eventoId;
}