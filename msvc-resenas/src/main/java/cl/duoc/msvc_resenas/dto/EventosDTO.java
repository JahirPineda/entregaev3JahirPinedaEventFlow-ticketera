package cl.duoc.msvc_resenas.dto;

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
    private boolean activo;
    private int categoriaId;
    private int recintoId;
}