package cl.duoc.msvc_exponentes.controller;

import cl.duoc.msvc_exponentes.dto.ExponentesDTO;
import cl.duoc.msvc_exponentes.service.ExponentesService;
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

@Tag(name = "Exponentes", description = "Operaciones de gestión de exponentes y artistas")
@RestController
@RequestMapping("/api/exponentes")
public class ExponentesController {

    @Autowired
    private ExponentesService service;

    @Operation(summary = "Obtener todos los exponentes", description = "Retorna la lista completa de exponentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<ExponentesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener exponente por ID", description = "Retorna un exponente específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exponente encontrado"),
            @ApiResponse(responseCode = "404", description = "Exponente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Exponente no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ExponentesDTO> getById(
            @Parameter(description = "ID del exponente", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear exponente", description = "Crea un nuevo exponente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Exponente creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"nombre\":\"El nombre es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<ExponentesDTO> create(@Valid @RequestBody ExponentesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar exponente", description = "Actualiza los datos de un exponente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exponente actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Exponente no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Exponente no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ExponentesDTO> update(
            @Parameter(description = "ID del exponente a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody ExponentesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar exponente", description = "Elimina un exponente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Exponente eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID del exponente a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Exponente eliminado");
    }

    @Operation(summary = "Buscar exponentes por evento", description = "Retorna exponentes filtrados por evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<ExponentesDTO>> getByEvento(
            @Parameter(description = "ID del evento", example = "1") @PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @Operation(summary = "Buscar exponentes por nacionalidad", description = "Retorna exponentes filtrados por nacionalidad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<ExponentesDTO>> getByNacionalidad(
            @Parameter(description = "Nacionalidad del exponente", example = "Chilena") @PathVariable String nacionalidad) {
        return ResponseEntity.ok(service.getByNacionalidad(nacionalidad));
    }

    @Operation(summary = "Buscar exponentes por nombre", description = "Retorna exponentes filtrados por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<ExponentesDTO>> getByNombre(
            @Parameter(description = "Nombre del exponente", example = "Juan") @PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @Operation(summary = "Buscar exponentes por apellido", description = "Retorna exponentes filtrados por apellido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/apellido/{apellido}")
    public ResponseEntity<List<ExponentesDTO>> getByApellido(
            @Parameter(description = "Apellido del exponente", example = "González") @PathVariable String apellido) {
        return ResponseEntity.ok(service.getByApellido(apellido));
    }
}