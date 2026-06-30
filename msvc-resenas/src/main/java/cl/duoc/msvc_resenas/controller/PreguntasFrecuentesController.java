package cl.duoc.msvc_resenas.controller;

import cl.duoc.msvc_resenas.dto.PreguntasFrecuentesDTO;
import cl.duoc.msvc_resenas.service.PreguntasFrecuentesService;
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

@Tag(name = "Preguntas Frecuentes", description = "Operaciones de gestión de preguntas frecuentes")
@RestController
@RequestMapping("/api/preguntas")
public class PreguntasFrecuentesController {

    @Autowired
    private PreguntasFrecuentesService service;

    @Operation(summary = "Obtener todas las preguntas", description = "Retorna la lista completa de preguntas frecuentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener pregunta por ID", description = "Retorna una pregunta frecuente específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pregunta encontrada"),
            @ApiResponse(responseCode = "404", description = "Pregunta no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Pregunta no encontrada con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PreguntasFrecuentesDTO> getById(
            @Parameter(description = "ID de la pregunta", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear pregunta", description = "Crea una nueva pregunta frecuente en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Pregunta creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"pregunta\":\"La pregunta es obligatoria\"}}")))
    })
    @PostMapping
    public ResponseEntity<PreguntasFrecuentesDTO> create(@Valid @RequestBody PreguntasFrecuentesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar pregunta", description = "Actualiza los datos de una pregunta frecuente existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pregunta actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Pregunta no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Pregunta no encontrada con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PreguntasFrecuentesDTO> update(
            @Parameter(description = "ID de la pregunta a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody PreguntasFrecuentesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar pregunta", description = "Elimina una pregunta frecuente del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pregunta eliminada exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID de la pregunta a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Pregunta eliminada");
    }

    @Operation(summary = "Buscar preguntas por categoría", description = "Retorna preguntas filtradas por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/categoria/{categoria}")
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getByCategoria(
            @Parameter(description = "Categoría de la pregunta", example = "Pagos") @PathVariable String categoria) {
        return ResponseEntity.ok(service.getByCategoria(categoria));
    }

    @Operation(summary = "Obtener preguntas activas", description = "Retorna todas las preguntas frecuentes con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/activas")
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getActivas() {
        return ResponseEntity.ok(service.getActivas());
    }
}