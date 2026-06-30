package cl.duoc.msvc_eventos.service;

import cl.duoc.msvc_eventos.dto.CategoriasDTO;
import cl.duoc.msvc_eventos.exception.ResourceNotFoundException;
import cl.duoc.msvc_eventos.model.Categorias;
import cl.duoc.msvc_eventos.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriasService {

    @Autowired
    private CategoriasRepository repository;

    private CategoriasDTO toDTO(Categorias c) {
        CategoriasDTO dto = new CategoriasDTO();
        dto.setId(c.getId());
        dto.setNombre(c.getNombre());
        dto.setDescripcion(c.getDescripcion());
        dto.setImagenUrl(c.getImagenUrl());
        dto.setActivo(c.isActivo());
        return dto;
    }

    private Categorias toEntity(CategoriasDTO dto) {
        Categorias c = new Categorias();
        c.setId(dto.getId());
        c.setNombre(dto.getNombre());
        c.setDescripcion(dto.getDescripcion());
        c.setImagenUrl(dto.getImagenUrl());
        c.setActivo(dto.isActivo());
        return c;
    }

    public List<CategoriasDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CategoriasDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id: " + id));
    }

    public List<CategoriasDTO> getActivas() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<CategoriasDTO> getByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public CategoriasDTO create(CategoriasDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public CategoriasDTO update(int id, CategoriasDTO dto) {
        Categorias c = toEntity(dto);
        c.setId(id);
        return toDTO(repository.save(c));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}