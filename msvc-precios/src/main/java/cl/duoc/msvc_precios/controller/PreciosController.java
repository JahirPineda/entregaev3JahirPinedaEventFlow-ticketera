package cl.duoc.msvc_precios.controller;

import cl.duoc.msvc_precios.dto.PreciosDTO;
import cl.duoc.msvc_precios.service.PreciosService;
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

@Tag(name = "Precios", description = "Operaciones de gestión de precios y tipos de entrada")
@RestController
@RequestMapping("/api/precios")
public class PreciosController {

    @Autowired
    private PreciosService service;

    @Operation(summary = "Obtener todos los precios", description = "Retorna la lista completa de precios")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<PreciosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener precio por ID", description = "Retorna un precio específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Precio encontrado"),
            @ApiResponse(responseCode = "404", description = "Precio no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Precio no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<PreciosDTO> getById(
            @Parameter(description = "ID del precio", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear precio", description = "Crea un nuevo precio en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Precio creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"valor\":\"El valor es obligatorio\"}}")))
    })
    @PostMapping
    public ResponseEntity<PreciosDTO> create(@Valid @RequestBody PreciosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar precio", description = "Actualiza los datos de un precio existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Precio actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Precio no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Precio no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<PreciosDTO> update(
            @Parameter(description = "ID del precio a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody PreciosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar precio", description = "Elimina un precio del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Precio eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID del precio a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Precio eliminado");
    }

    @Operation(summary = "Buscar precios por evento", description = "Retorna precios filtrados por evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<PreciosDTO>> getByEvento(
            @Parameter(description = "ID del evento", example = "1") @PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @Operation(summary = "Buscar precios por tipo de entrada", description = "Retorna precios filtrados por tipo de entrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/tipo/{tipoEntrada}")
    public ResponseEntity<List<PreciosDTO>> getByTipo(
            @Parameter(description = "Tipo de entrada", example = "VIP") @PathVariable String tipoEntrada) {
        return ResponseEntity.ok(service.getByTipoEntrada(tipoEntrada));
    }

    @Operation(summary = "Obtener precios activos", description = "Retorna todos los precios con estado activo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/activos")
    public ResponseEntity<List<PreciosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @Operation(summary = "Buscar precios por valor máximo", description = "Retorna precios con valor menor o igual al indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/maximo/{valor}")
    public ResponseEntity<List<PreciosDTO>> getByValorMaximo(
            @Parameter(description = "Valor máximo del precio", example = "50000") @PathVariable double valor) {
        return ResponseEntity.ok(service.getByValorMaximo(valor));
    }

    @Operation(summary = "Obtener precios con stock disponible", description = "Retorna precios que tienen stock mayor a 0")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/con-stock")
    public ResponseEntity<List<PreciosDTO>> getConStock() {
        return ResponseEntity.ok(service.getConStock());
    }
}