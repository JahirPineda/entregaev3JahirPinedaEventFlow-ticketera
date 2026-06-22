package cl.duoc.msvc_ordenes.dto;

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