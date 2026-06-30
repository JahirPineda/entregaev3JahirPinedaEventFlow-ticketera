package cl.duoc.msvc_eventos.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "eventos")
public class Eventos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(length = 1000)
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fecha;

    private LocalTime hora;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(nullable = false)
    private boolean activo;

    @Column(name = "categoria_id")
    private int categoriaId;

    @Column(name = "recinto_id")
    private int recintoId;
}