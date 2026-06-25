package cl.duoc.msvc_recintos.controller;

import cl.duoc.msvc_recintos.dto.RecintosDTO;
import cl.duoc.msvc_recintos.service.RecintosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recintos")
public class RecintosController {

    @Autowired
    private RecintosService service;

    @GetMapping
    public ResponseEntity<List<RecintosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecintosDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<RecintosDTO> create(@Valid @RequestBody RecintosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecintosDTO> update(@PathVariable int id, @Valid @RequestBody RecintosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Recinto eliminado");
    }

    // Reportes
    @GetMapping("/reporte/ciudad/{ciudad}")
    public ResponseEntity<List<RecintosDTO>> getByCiudad(@PathVariable String ciudad) {
        return ResponseEntity.ok(service.getByCiudad(ciudad));
    }

    @GetMapping("/reporte/activos")
    public ResponseEntity<List<RecintosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @GetMapping("/reporte/capacidad/{capacidad}")
    public ResponseEntity<List<RecintosDTO>> getByCapacidad(@PathVariable int capacidad) {
        return ResponseEntity.ok(service.getByCapacidadMinima(capacidad));
    }

    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<RecintosDTO>> getByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }
}