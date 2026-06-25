package cl.duoc.msvc_resenas.controller;

import cl.duoc.msvc_resenas.dto.ResenasDTO;
import cl.duoc.msvc_resenas.service.ResenasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
public class ResenasController {

    @Autowired
    private ResenasService service;

    @GetMapping
    public ResponseEntity<List<ResenasDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResenasDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ResenasDTO> create(@Valid @RequestBody ResenasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResenasDTO> update(@PathVariable int id, @Valid @RequestBody ResenasDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Reseña eliminada");
    }

    // Reportes
    @GetMapping("/reporte/cliente/{clienteId}")
    public ResponseEntity<List<ResenasDTO>> getByCliente(@PathVariable int clienteId) {
        return ResponseEntity.ok(service.getByCliente(clienteId));
    }

    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<ResenasDTO>> getByEvento(@PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @GetMapping("/reporte/puntuacion/{puntuacion}")
    public ResponseEntity<List<ResenasDTO>> getByPuntuacion(@PathVariable int puntuacion) {
        return ResponseEntity.ok(service.getByPuntuacion(puntuacion));
    }

    @GetMapping("/reporte/puntuacion-minima/{puntuacion}")
    public ResponseEntity<List<ResenasDTO>> getByPuntuacionMinima(@PathVariable int puntuacion) {
        return ResponseEntity.ok(service.getByPuntuacionMinima(puntuacion));
    }
}