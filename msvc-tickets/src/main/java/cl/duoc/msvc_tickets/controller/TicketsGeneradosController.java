package cl.duoc.msvc_tickets.controller;

import cl.duoc.msvc_tickets.dto.TicketsGeneradosDTO;
import cl.duoc.msvc_tickets.service.TicketsGeneradosService;
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

@Tag(name = "Tickets", description = "Operaciones de gestión de tickets generados")
@RestController
@RequestMapping("/api/tickets")
public class TicketsGeneradosController {

    @Autowired
    private TicketsGeneradosService service;

    @Operation(summary = "Obtener todos los tickets", description = "Retorna la lista completa de tickets generados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<TicketsGeneradosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener ticket por ID", description = "Retorna un ticket específico por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Ticket no encontrado con id: 1\"}")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<TicketsGeneradosDTO> getById(
            @Parameter(description = "ID del ticket", example = "1") @PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear ticket", description = "Genera un nuevo ticket en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":400,\"error\":\"Error de validación\",\"campos\":{\"ordenId\":\"La orden es obligatoria\"}}")))
    })
    @PostMapping
    public ResponseEntity<TicketsGeneradosDTO> create(@Valid @RequestBody TicketsGeneradosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar ticket", description = "Actualiza los datos de un ticket existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket actualizado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Ticket no encontrado con id: 1\"}")))
    })
    @PutMapping("/{id}")
    public ResponseEntity<TicketsGeneradosDTO> update(
            @Parameter(description = "ID del ticket a actualizar", example = "1") @PathVariable int id,
            @Valid @RequestBody TicketsGeneradosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar ticket", description = "Elimina un ticket del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket eliminado exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(
            @Parameter(description = "ID del ticket a eliminar", example = "1") @PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Ticket eliminado");
    }

    @Operation(summary = "Buscar tickets por cliente", description = "Retorna tickets filtrados por cliente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/cliente/{clienteId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByCliente(
            @Parameter(description = "ID del cliente", example = "1") @PathVariable int clienteId) {
        return ResponseEntity.ok(service.getByCliente(clienteId));
    }

    @Operation(summary = "Buscar tickets por evento", description = "Retorna tickets filtrados por evento")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByEvento(
            @Parameter(description = "ID del evento", example = "1") @PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @Operation(summary = "Buscar tickets por orden", description = "Retorna tickets filtrados por orden de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/orden/{ordenId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByOrden(
            @Parameter(description = "ID de la orden", example = "1") @PathVariable int ordenId) {
        return ResponseEntity.ok(service.getByOrden(ordenId));
    }

    @Operation(summary = "Buscar ticket por código QR", description = "Retorna un ticket específico por su código QR")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket encontrado"),
            @ApiResponse(responseCode = "404", description = "Ticket no encontrado",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\":\"2024-01-01T00:00:00\",\"status\":404,\"error\":\"No encontrado\",\"mensaje\":\"Ticket no encontrado con código QR\"}")))
    })
    @GetMapping("/reporte/qr/{codigoQR}")
    public ResponseEntity<TicketsGeneradosDTO> getByCodigoQR(
            @Parameter(description = "Código QR del ticket", example = "QR-2024-001") @PathVariable String codigoQR) {
        return ResponseEntity.ok(service.getByCodigoQR(codigoQR));
    }

    @Operation(summary = "Obtener tickets usados", description = "Retorna todos los tickets que ya fueron utilizados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/usados")
    public ResponseEntity<List<TicketsGeneradosDTO>> getUsados() {
        return ResponseEntity.ok(service.getUsados());
    }

    @Operation(summary = "Obtener tickets no usados", description = "Retorna todos los tickets que aún no han sido utilizados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/no-usados")
    public ResponseEntity<List<TicketsGeneradosDTO>> getNoUsados() {
        return ResponseEntity.ok(service.getNoUsados());
    }

    @Operation(summary = "Buscar tickets por tipo de entrada", description = "Retorna tickets filtrados por tipo de entrada")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/tipo/{tipoEntrada}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByTipo(
            @Parameter(description = "Tipo de entrada", example = "VIP") @PathVariable String tipoEntrada) {
        return ResponseEntity.ok(service.getByTipoEntrada(tipoEntrada));
    }
}