package cl.duoc.msvc_resenas.dto;

import lombok.Data;

@Data
public class ClientesDTO {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String tipoCliente;
}