package cl.duoc.msvc_eventos.controller;

import cl.duoc.msvc_eventos.dto.CategoriasDTO;
import cl.duoc.msvc_eventos.service.CategoriasService;
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

@Tag(name = "Categorías", description = "Operaciones de gestión de categorías de eventos")
@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;

    @Operation(summary = "Obtener todas las categorías", description = "Retorna la lista completa de categorías")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener categoría por ID", description = "Retorna una categoría específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría encontrada"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Categoría no encontrada con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDTO> getById(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear categoría", description = "Crea una nueva categoría en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Categoría creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"nombre\":\"El nombre es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<CategoriasDTO> create(@Valid @RequestBody CategoriasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar categoría", description = "Actualiza los datos de una categoría existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Categoría no encontrada",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Categoría no encontrada con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDTO> update(
            @Parameter(description = "ID de la categoría a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody CategoriasDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar categoría", description = "Elimina una categoría del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Categoría eliminada exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID de la categoría a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Categoría eliminada");
    }

    @Operation(summary = "Obtener categorías activas", description = "Retorna todas las categorías con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/activas")
    public ResponseEntity<List<CategoriasDTO>> getActivas() {
        return ResponseEntity.ok(service.getActivas());
    }

    @Operation(summary = "Buscar categorías por nombre", description = "Retorna categorías filtradas por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<CategoriasDTO>> getByNombre(
            @Parameter(description = "Nombre de la categoría", example = "Música") @PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}