package cl.duoc.msvc_resenas.controller;

import cl.duoc.msvc_resenas.dto.PreguntasFrecuentesDTO;
import cl.duoc.msvc_resenas.service.PreguntasFrecuentesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/preguntas")
public class PreguntasFrecuentesController {

    @Autowired
    private PreguntasFrecuentesService service;

    @GetMapping
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreguntasFrecuentesDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PreguntasFrecuentesDTO> create(@Valid @RequestBody PreguntasFrecuentesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreguntasFrecuentesDTO> update(@PathVariable int id, @Valid @RequestBody PreguntasFrecuentesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Pregunta eliminada");
    }

    // Reportes
    @GetMapping("/reporte/categoria/{categoria}")
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getByCategoria(@PathVariable String categoria) {
        return ResponseEntity.ok(service.getByCategoria(categoria));
    }

    @GetMapping("/reporte/activas")
    public ResponseEntity<List<PreguntasFrecuentesDTO>> getActivas() {
        return ResponseEntity.ok(service.getActivas());
    }
}