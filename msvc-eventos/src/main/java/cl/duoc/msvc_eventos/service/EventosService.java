package cl.duoc.msvc_eventos.service;

import cl.duoc.msvc_eventos.dto.EventosDTO;
import cl.duoc.msvc_eventos.exception.ResourceNotFoundException;
import cl.duoc.msvc_eventos.model.Eventos;
import cl.duoc.msvc_eventos.repository.EventosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventosService {

    @Autowired
    private EventosRepository repository;

    private EventosDTO toDTO(Eventos e) {
        EventosDTO dto = new EventosDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setDescripcion(e.getDescripcion());
        dto.setFecha(e.getFecha());
        dto.setHora(e.getHora());
        dto.setImagenUrl(e.getImagenUrl());
        dto.setActivo(e.isActivo());
        dto.setCategoriaId(e.getCategoriaId());
        dto.setRecintoId(e.getRecintoId());
        return dto;
    }

    private Eventos toEntity(EventosDTO dto) {
        Eventos e = new Eventos();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setDescripcion(dto.getDescripcion());
        e.setFecha(dto.getFecha());
        e.setHora(dto.getHora());
        e.setImagenUrl(dto.getImagenUrl());
        e.setActivo(dto.isActivo());
        e.setCategoriaId(dto.getCategoriaId());
        e.setRecintoId(dto.getRecintoId());
        return e;
    }

    public List<EventosDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EventosDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Evento no encontrado con id: " + id));
    }

    public List<EventosDTO> getByCategoria(int categoriaId) {
        return repository.findByCategoriaId(categoriaId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EventosDTO> getByFecha(LocalDate fecha) {
        return repository.findByFecha(fecha).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EventosDTO> getActivos() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EventosDTO> getByRangoFechas(LocalDate inicio, LocalDate fin) {
        return repository.findByFechaBetween(inicio, fin).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<EventosDTO> getByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public EventosDTO create(EventosDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public EventosDTO update(int id, EventosDTO dto) {
        Eventos e = toEntity(dto);
        e.setId(id);
        return toDTO(repository.save(e));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}