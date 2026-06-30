package cl.duoc.msvc_recintos.controller;

import cl.duoc.msvc_recintos.dto.RecintosDTO;
import cl.duoc.msvc_recintos.service.RecintosService;
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

@Tag(name = "Recintos", description = "Operaciones de gestión de recintos y venues")
@RestController
@RequestMapping("/api/recintos")
public class RecintosController {

    @Autowired
    private RecintosService service;

    @Operation(summary = "Obtener todos los recintos", description = "Retorna la lista completa de recintos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<RecintosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener recinto por ID", description = "Retorna un recinto específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recinto encontrado"),
            @ApiResponse(responseCode = "404", description = "Recinto no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Recinto no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<RecintosDTO> getById(
            @Parameter(description = "ID del recinto", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear recinto", description = "Crea un nuevo recinto en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Recinto creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"nombre\":\"El nombre es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<RecintosDTO> create(@Valid @RequestBody RecintosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar recinto", description = "Actualiza los datos de un recinto existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recinto actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Recinto no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Recinto no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<RecintosDTO> update(
            @Parameter(description = "ID del recinto a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody RecintosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar recinto", description = "Elimina un recinto del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Recinto eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID del recinto a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Recinto eliminado");
    }

    @Operation(summary = "Buscar recintos por ciudad", description = "Retorna recintos filtrados por ciudad")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/ciudad/{ciudad}")
    public ResponseEntity<List<RecintosDTO>> getByCiudad(
            @Parameter(description = "Ciudad del recinto", example = "Santiago") @PathVariable String ciudad) {
        return ResponseEntity.ok(service.getByCiudad(ciudad));
    }

    @Operation(summary = "Obtener recintos activos", description = "Retorna todos los recintos con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/activos")
    public ResponseEntity<List<RecintosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @Operation(summary = "Buscar recintos por capacidad mínima", description = "Retorna recintos con capacidad mayor o igual al valor indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/capacidad/{capacidad}")
    public ResponseEntity<List<RecintosDTO>> getByCapacidad(
            @Parameter(description = "Capacidad mínima del recinto", example = "1000") @PathVariable int capacidad) {
        return ResponseEntity.ok(service.getByCapacidadMinima(capacidad));
    }

    @Operation(summary = "Buscar recintos por nombre", description = "Retorna recintos filtrados por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<RecintosDTO>> getByNombre(
            @Parameter(description = "Nombre del recinto", example = "Estadio Nacional") @PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}