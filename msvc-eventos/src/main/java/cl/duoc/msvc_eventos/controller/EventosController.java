package cl.duoc.msvc_eventos.controller;

import cl.duoc.msvc_eventos.dto.EventosDTO;
import cl.duoc.msvc_eventos.service.EventosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
public class EventosController {

    @Autowired
    private EventosService service;

    @GetMapping
    public ResponseEntity<List<EventosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventosDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<EventosDTO> create(@Valid @RequestBody EventosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventosDTO> update(@PathVariable int id, @Valid @RequestBody EventosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Evento eliminado");
    }

    // Reportes
    @GetMapping("/reporte/activos")
    public ResponseEntity<List<EventosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @GetMapping("/reporte/categoria/{categoriaId}")
    public ResponseEntity<List<EventosDTO>> getByCategoria(@PathVariable int categoriaId) {
        return ResponseEntity.ok(service.getByCategoria(categoriaId));
    }

    @GetMapping("/reporte/fecha/{fecha}")
    public ResponseEntity<List<EventosDTO>> getByFecha(@PathVariable String fecha) {
        return ResponseEntity.ok(service.getByFecha(LocalDate.parse(fecha)));
    }

    @GetMapping("/reporte/rango")
    public ResponseEntity<List<EventosDTO>> getByRangoFechas(
            @RequestParam String inicio,
            @RequestParam String fin) {
        return ResponseEntity.ok(service.getByRangoFechas(LocalDate.parse(inicio), LocalDate.parse(fin)));
    }

    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<EventosDTO>> getByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}