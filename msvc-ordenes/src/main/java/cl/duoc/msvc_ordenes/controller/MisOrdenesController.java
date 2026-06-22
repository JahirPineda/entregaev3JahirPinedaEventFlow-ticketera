package cl.duoc.msvc_ordenes.controller;

import cl.duoc.msvc_ordenes.dto.MisOrdenesDTO;
import cl.duoc.msvc_ordenes.service.MisOrdenesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Órdenes", description = "Operaciones de gestión de órdenes de compra")
@RestController
@RequestMapping("/api/ordenes")
public class MisOrdenesController {

    @Autowired
    private MisOrdenesService service;

    @Operation(summary = "Obtener todas las órdenes", description = "Retorna la lista completa de órdenes de compra")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping
    public ResponseEntity<List<MisOrdenesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Obtener orden por ID", description = "Retorna una orden específica por su ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden encontrada"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<MisOrdenesDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @Operation(summary = "Crear orden", description = "Crea una nueva orden de compra en el sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Orden creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos")
    })
    @PostMapping
    public ResponseEntity<MisOrdenesDTO> create(@Valid @RequestBody MisOrdenesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @Operation(summary = "Actualizar orden", description = "Actualiza los datos de una orden existente")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden actualizada exitosamente"),
            @ApiResponse(responseCode = "404", description = "Orden no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<MisOrdenesDTO> update(@PathVariable int id, @Valid @RequestBody MisOrdenesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @Operation(summary = "Eliminar orden", description = "Elimina una orden del sistema")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orden eliminada exitosamente")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Orden eliminada");
    }

    @Operation(summary = "Buscar órdenes por cliente", description = "Retorna todas las órdenes de un cliente específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/cliente/{clienteId}")
    public ResponseEntity<List<MisOrdenesDTO>> getByCliente(@PathVariable int clienteId) {
        return ResponseEntity.ok(service.getByCliente(clienteId));
    }

    @Operation(summary = "Buscar órdenes por evento", description = "Retorna todas las órdenes de un evento específico")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<MisOrdenesDTO>> getByEvento(@PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @Operation(summary = "Buscar órdenes por estado", description = "Retorna órdenes filtradas por estado (PAGADA, PENDIENTE, CANCELADA)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/estado/{estado}")
    public ResponseEntity<List<MisOrdenesDTO>> getByEstado(@PathVariable String estado) {
        return ResponseEntity.ok(service.getByEstado(estado));
    }

    @Operation(summary = "Buscar órdenes por método de pago", description = "Retorna órdenes filtradas por método de pago (TARJETA, TRANSFERENCIA)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/metodo/{metodoPago}")
    public ResponseEntity<List<MisOrdenesDTO>> getByMetodoPago(@PathVariable String metodoPago) {
        return ResponseEntity.ok(service.getByMetodoPago(metodoPago));
    }

    @Operation(summary = "Buscar órdenes por total mínimo", description = "Retorna órdenes con total mayor o igual al valor indicado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida exitosamente")
    })
    @GetMapping("/reporte/total/{total}")
    public ResponseEntity<List<MisOrdenesDTO>> getByTotalMinimo(@PathVariable double total) {
        return ResponseEntity.ok(service.getByTotalMinimo(total));
    }
}