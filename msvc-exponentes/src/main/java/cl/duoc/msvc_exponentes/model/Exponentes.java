package cl.duoc.msvc_exponentes.model;

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
@Table(name = "exponentes")
public class Exponentes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;

    @Column(length = 1000)
    private String biografia;

    private String nacionalidad;

    @Column(name = "imagen_url")
    private String imagenUrl;

    @Column(name = "redes_sociales")
    private String redesSociales;

    @Column(name = "evento_id")
    private int eventoId;
}