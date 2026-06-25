package cl.duoc.msvc_precios.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "precios")
public class Precios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "tipo_entrada", nullable = false)
    private String tipoEntrada;

    @Column(nullable = false)
    private double valor;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private boolean activo;

    @Column(name = "evento_id")
    private int eventoId;
}