package cl.duoc.msvc_precios.controller;

import cl.duoc.msvc_precios.dto.PreciosDTO;
import cl.duoc.msvc_precios.service.PreciosService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/precios")
public class PreciosController {

    @Autowired
    private PreciosService service;

    @GetMapping
    public ResponseEntity<List<PreciosDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PreciosDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PreciosDTO> create(@Valid @RequestBody PreciosDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PreciosDTO> update(@PathVariable int id, @Valid @RequestBody PreciosDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Precio eliminado");
    }

    // Reportes
    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<PreciosDTO>> getByEvento(@PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @GetMapping("/reporte/tipo/{tipoEntrada}")
    public ResponseEntity<List<PreciosDTO>> getByTipo(@PathVariable String tipoEntrada) {
        return ResponseEntity.ok(service.getByTipoEntrada(tipoEntrada));
    }

    @GetMapping("/reporte/activos")
    public ResponseEntity<List<PreciosDTO>> getActivos() {
        return ResponseEntity.ok(service.getActivos());
    }

    @GetMapping("/reporte/maximo/{valor}")
    public ResponseEntity<List<PreciosDTO>> getByValorMaximo(@PathVariable double valor) {
        return ResponseEntity.ok(service.getByValorMaximo(valor));
    }

    @GetMapping("/reporte/con-stock")
    public ResponseEntity<List<PreciosDTO>> getConStock() {
        return ResponseEntity.ok(service.getConStock());
    }
}