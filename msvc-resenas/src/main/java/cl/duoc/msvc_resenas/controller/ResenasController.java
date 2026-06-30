package cl.duoc.msvc_resenas.controller;

import cl.duoc.msvc_resenas.dto.ResenasDTO;
import cl.duoc.msvc_resenas.service.ResenasService;
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

@Tag(name = "Reseñas", description = "Operaciones de gestión de reseñas de eventos")
@RestController
@RequestMapping("/api/resenas")
public class ResenasController {

    @Autowired
    private ResenasService service;

    @Operation(summary = "Obtener todas las reseñas", description = "Retorna la lista completa de reseñas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<ResenasDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener reseña por ID", description = "Retorna una reseña específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña encontrada"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Reseña no encontrada con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<ResenasDTO> getById(
            @Parameter(description = "ID de la reseña", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear reseña", description = "Crea una nueva reseña en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Reseña creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"comentario\":\"El comentario es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<ResenasDTO> create(@Valid @RequestBody ResenasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar reseña", description = "Actualiza los datos de una reseña existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Reseña no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Reseña no encontrada con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResenasDTO> update(
            @Parameter(description = "ID de la reseña a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody ResenasDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar reseña", description = "Elimina una reseña del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reseña eliminada exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID de la reseña a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Reseña eliminada");
    }

    @Operation(summary = "Buscar reseñas por cliente", description = "Retorna reseñas filtradas por cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/cliente/{clienteId}")
    public ResponseEntity<List<ResenasDTO>> getByCliente(
            @Parameter(description = "ID del cliente", example = "1") @PathVariable int clienteId) {
        return ResponseEntity.ok(service.getByCliente(clienteId));
    }

    @Operation(summary = "Buscar reseñas por evento", description = "Retorna reseñas filtradas por evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<ResenasDTO>> getByEvento(
            @Parameter(description = "ID del evento", example = "1") @PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @Operation(summary = "Buscar reseñas por puntuación", description = "Retorna reseñas filtradas por puntuación exacta")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/puntuacion/{puntuacion}")
    public ResponseEntity<List<ResenasDTO>> getByPuntuacion(
            @Parameter(description = "Puntuación exacta (1-5)", example = "5") @PathVariable int puntuacion) {
        return ResponseEntity.ok(service.getByPuntuacion(puntuacion));
    }

    @Operation(summary = "Buscar reseñas por puntuación mínima", description = "Retorna reseñas con puntuación mayor o igual al valor indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/puntuacion-minima/{puntuacion}")
    public ResponseEntity<List<ResenasDTO>> getByPuntuacionMinima(
            @Parameter(description = "Puntuación mínima (1-5)", example = "4") @PathVariable int puntuacion) {
        return ResponseEntity.ok(service.getByPuntuacionMinima(puntuacion));
    }
}