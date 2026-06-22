package cl.duoc.msvc_eventos.controller;

import cl.duoc.msvc_eventos.dto.CategoriasDTO;
import cl.duoc.msvc_eventos.service.CategoriasService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
public class CategoriasController {

    @Autowired
    private CategoriasService service;

    @GetMapping
    public ResponseEntity<List<CategoriasDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriasDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CategoriasDTO> create(@Valid @RequestBody CategoriasDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriasDTO> update(@PathVariable int id, @Valid @RequestBody CategoriasDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Categoría eliminada");
    }

    // Reportes
    @GetMapping("/reporte/activas")
    public ResponseEntity<List<CategoriasDTO>> getActivas() {
        return ResponseEntity.ok(service.getActivas());
    }

    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<CategoriasDTO>> getByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}