package cl.duoc.msvc_clientes.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "ID generado automáticamente")
    private int id;

    @NotBlank(message = "El RUT es obligatorio")
    @Schema(description = "RUT del cliente", example = "12.345.678-9")
    private String rut;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    @Schema(description = "Nombre del cliente", example = "Juan")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    @Schema(description = "Apellido del cliente", example = "González")
    private String apellido;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email no es válido")
    @Schema(description = "Email del cliente", example = "juan.gonzalez@mail.com")
    private String email;

    @Pattern(regexp = "^[0-9]{9}$", message = "El teléfono debe tener 9 dígitos")
    @Schema(description = "Teléfono del cliente (9 dígitos)", example = "912345678")
    private String telefono;

    @Schema(description = "Dirección del cliente", example = "Av. Providencia 123")
    private String direccion;

    @Schema(description = "Ciudad del cliente", example = "Santiago")
    private String ciudad;

    @NotBlank(message = "El username es obligatorio")
    @Schema(description = "Nombre de usuario", example = "juangonzalez")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    @Schema(description = "Contraseña del cliente (mínimo 6 caracteres)", example = "pass123")
    private String password;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Fecha de registro generada automáticamente")
    private LocalDate fechaRegistro;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado del cliente generado automáticamente")
    private boolean activo;

    @Schema(description = "Tipo de cliente", example = "REGULAR")
    private String tipoCliente;
}