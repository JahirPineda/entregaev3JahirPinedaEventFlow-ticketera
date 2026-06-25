package cl.duoc.msvc_exponentes.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class EventosDTO {
    private int id;
    private String nombre;
    private String descripcion;
    private LocalDate fecha;
    private LocalTime hora;
    private String imagenUrl;
    private boolean activo;
    private int categoriaId;
    private int recintoId;
}