package cl.duoc.msvc_resenas.model;

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
@Table(name = "resenas")
public class Resenas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int puntuacion;

    @Column(length = 1000)
    private String comentario;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(name = "cliente_id")
    private int clienteId;

    @Column(name = "evento_id")
    private int eventoId;
}