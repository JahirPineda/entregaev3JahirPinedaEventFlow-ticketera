package cl.duoc.msvc_exponentes.controller;

import cl.duoc.msvc_exponentes.dto.ExponentesDTO;
import cl.duoc.msvc_exponentes.service.ExponentesService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exponentes")
public class ExponentesController {

    @Autowired
    private ExponentesService service;

    @GetMapping
    public ResponseEntity<List<ExponentesDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ExponentesDTO> getById(@PathVariable int id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ExponentesDTO> create(@Valid @RequestBody ExponentesDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExponentesDTO> update(@PathVariable int id, @Valid @RequestBody ExponentesDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable int id) {
        service.delete(id);
        return ResponseEntity.ok("Exponente eliminado");
    }

    @GetMapping("/reporte/evento/{eventoId}")
    public ResponseEntity<List<ExponentesDTO>> getByEvento(@PathVariable int eventoId) {
        return ResponseEntity.ok(service.getByEvento(eventoId));
    }

    @GetMapping("/reporte/nacionalidad/{nacionalidad}")
    public ResponseEntity<List<ExponentesDTO>> getByNacionalidad(@PathVariable String nacionalidad) {
        return ResponseEntity.ok(service.getByNacionalidad(nacionalidad));
    }

    @GetMapping("/reporte/nombre/{nombre}")
    public ResponseEntity<List<ExponentesDTO>> getByNombre(@PathVariable String nombre) {
        return ResponseEntity.ok(service.getByNombre(nombre));
    }

    @GetMapping("/reporte/apellido/{apellido}")
    public ResponseEntity<List<ExponentesDTO>> getByApellido(@PathVariable String apellido) {
        return ResponseEntity.ok(service.getByApellido(apellido));
    }
}