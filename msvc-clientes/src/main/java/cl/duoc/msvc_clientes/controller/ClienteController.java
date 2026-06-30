package cl.duoc.msvc_clientes.controller;

import cl.duoc.msvc_clientes.dto.ClienteDTO;
import cl.duoc.msvc_clientes.exception.ResourceNotFoundException;
import cl.duoc.msvc_clientes.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Operaciones de gestión de clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @Operation(summary = "Obtener todos los clientes", description = "Retorna la lista completa de clientes registrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)))
    })
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener cliente por ID", description = "Retorna un cliente específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Cliente no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> getById(
            @Parameter(description = "ID del cliente", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear cliente", description = "Crea un nuevo cliente en el sistema. El ID es generado automáticamente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"nombre\":\"El nombre es obligatorio\",\"email\":\"El email no es válido\",\"rut\":\"El RUT es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza los datos de un cliente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Cliente no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(
            @Parameter(description = "ID del cliente a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody ClienteDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @Parameter(description = "ID del cliente a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar cliente por email", description = "Retorna un cliente según su email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Cliente no encontrado con email: juan@mail.com\"}")))
    })
    @GetMapping("/reporte/email/{email}")
    public ResponseEntity<ClienteDTO> getByEmail(
            @Parameter(description = "Email del cliente", example = "juan.gonzalez@mail.com") @PathVariable String email) {
        return service.getByEmail(email)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con email: " + email));
    }

    @Operation(summary = "Buscar cliente por RUT", description = "Retorna un cliente según su RUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))),
            @ApiResponse(responseCode = "404", description = "Cliente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Cliente no encontrado con rut: 12.345.678-9\"}")))
    })
    @GetMapping("/reporte/rut/{rut}")
    public ResponseEntity<ClienteDTO> getByRut(
            @Parameter(description = "RUT del cliente", example = "12.345.678-9") @PathVariable String rut) {
        return service.getByRut(rut)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con rut: " + rut));
    }

    @Operation(summary = "Buscar clientes por tipo", description = "Retorna clientes filtrados por tipo (REGULAR, VIP)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)))
    })
    @GetMapping("/reporte/tipo/{tipoCliente}")
    public ResponseEntity<List<ClienteDTO>> getByTipo(
            @Parameter(description = "Tipo de cliente", example = "REGULAR") @PathVariable String tipoCliente) {
        return ResponseEntity.ok(service.getByTipo(tipoCliente));
    }

    @Operation(summary = "Obtener clientes activos", description = "Retorna todos los clientes con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)))
    })
    @GetMapping("/reporte/activos")
    public ResponseEntity<List<ClienteDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @Operation(summary = "Buscar clientes por ciudad", description = "Retorna clientes filtrados por ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class)))
    })
    @GetMapping("/reporte/ciudad/{ciudad}")
    public ResponseEntity<List<ClienteDTO>> getByCiudad(
            @Parameter(description = "Ciudad del cliente", example = "Santiago") @PathVariable String ciudad) {
        return ResponseEntity.ok(service.getByCiudad(ciudad));
    }
}