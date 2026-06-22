package cl.duoc.msvc_resenas.model;

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
@Table(name = "preguntas_frecuentes")
public class PreguntasFrecuentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 500)
    private String pregunta;

    @Column(nullable = false, length = 1000)
    private String respuesta;

    private String categoria;

    @Column(nullable = false)
    private int orden;

    @Column(nullable = false)
    private boolean activo;
}