package cl.duoc.msvc_tickets.controller;

import cl.duoc.msvc_tickets.dto.TicketsGeneradosDTO;
import cl.duoc.msvc_tickets.service.TicketsGeneradosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class TicketsGeneradosController {

    @Autowired
    private TicketsGeneradosService service;

    @GetMapping
    public ResponseEntity<List<TicketsGeneradosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TicketsGeneradosDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TicketsGeneradosDTO> create(@Valid @RequestBody TicketsGeneradosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TicketsGeneradosDTO> update(@PathVariable int id, @Valid @RequestBody TicketsGeneradosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Ticket eliminado");
    }

    // Reportes
    @GetMapping("/reporte/cliente/{clienteId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByCliente(@PathVariable int clienteId) {
        return ResponseEntity.ok(service.getByCliente(clienteId));
    }

    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByEvento(@PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @GetMapping("/reporte/orden/{ordenId}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByOrden(@PathVariable int ordenId) {
        return ResponseEntity.ok(service.getByOrden(ordenId));
    }

    @GetMapping("/reporte/qr/{codigoQR}")
    public ResponseEntity<TicketsGeneradosDTO> getByCodigoQR(@PathVariable String codigoQR) {
        return ResponseEntity.ok(service.getByCodigoQR(codigoQR));
    }

    @GetMapping("/reporte/usados")
    public ResponseEntity<List<TicketsGeneradosDTO>> getUsados() {
        return ResponseEntity.ok(service.getUsados());
    }

    @GetMapping("/reporte/no-usados")
    public ResponseEntity<List<TicketsGeneradosDTO>> getNoUsados() {
        return ResponseEntity.ok(service.getNoUsados());
    }

    @GetMapping("/reporte/tipo/{tipoEntrada}")
    public ResponseEntity<List<TicketsGeneradosDTO>> getByTipo(@PathVariable String tipoEntrada) {
        return ResponseEntity.ok(service.getByTipoEntrada(tipoEntrada));
    }
}