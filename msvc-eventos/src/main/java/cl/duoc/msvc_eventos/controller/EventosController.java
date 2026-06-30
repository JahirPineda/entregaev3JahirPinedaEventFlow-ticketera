package cl.duoc.msvc_eventos.controller;

import cl.duoc.msvc_eventos.dto.EventosDTO;
import cl.duoc.msvc_eventos.service.EventosService;
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

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Eventos", description = "Operaciones de gestión de eventos")
@RestController
@RequestMapping("/api/eventos")
public class EventosController {

    @Autowired
    private EventosService service;

    @Operation(summary = "Obtener todos los eventos", description = "Retorna la lista completa de eventos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<EventosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener evento por ID", description = "Retorna un evento específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento encontrado"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Evento no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<EventosDTO> getById(
            @Parameter(description = "ID del evento", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear evento", description = "Crea un nuevo evento en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Evento creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"nombre\":\"El nombre es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<EventosDTO> create(@Valid @RequestBody EventosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar evento", description = "Actualiza los datos de un evento existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Evento no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Evento no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<EventosDTO> update(
            @Parameter(description = "ID del evento a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody EventosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar evento", description = "Elimina un evento del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Evento eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID del evento a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Evento eliminado");
    }

    @Operation(summary = "Obtener eventos activos", description = "Retorna todos los eventos con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/activos")
    public ResponseEntity<List<EventosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @Operation(summary = "Buscar eventos por categoría", description = "Retorna eventos filtrados por categoría")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/categoria/{categoriaId}")
    public ResponseEntity<List<EventosDTO>> getByCategoria(
            @Parameter(description = "ID de la categoría", example = "1") @PathVariable int categoriaId) {
        return ResponseEntity.ok(service.getByCategoria(categoriaId));
    }

    @Operation(summary = "Buscar eventos por fecha", description = "Retorna eventos filtrados por fecha (formato: yyyy-MM-dd)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/fecha/{fecha}")
    public ResponseEntity<List<EventosDTO>> getByFecha(
            @Parameter(description = "Fecha del evento", example = "2024-12-01") @PathVariable String fecha) {
        return ResponseEntity.ok(service.getByFecha(LocalDate.parse(fecha)));
    }

    @Operation(summary = "Buscar eventos por rango de fechas", description = "Retorna eventos dentro de un rango de fechas (formato: yyyy-MM-dd)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/rango")
    public ResponseEntity<List<EventosDTO>> getByRangoFechas(
            @Parameter(description = "Fecha de inicio", example = "2024-01-01") @RequestParam String inicio,
            @Parameter(description = "Fecha de fin", example = "2024-12-31") @RequestParam String fin) {
        return ResponseEntity.ok(service.getByRangoFechas(LocalDate.parse(inicio), LocalDate.parse(fin)));
    }

    @Operation(summary = "Buscar eventos por nombre", description = "Retorna eventos filtrados por nombre")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<EventosDTO>> getByNombre(
            @Parameter(description = "Nombre del evento", example = "Festival Rock 2024") @PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}