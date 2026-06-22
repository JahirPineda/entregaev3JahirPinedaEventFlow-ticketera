package cl.duoc.msvc_resenas.service;

import cl.duoc.msvc_resenas.dto.PreguntasFrecuentesDTO;
import cl.duoc.msvc_resenas.exception.ResourceNotFoundException;
import cl.duoc.msvc_resenas.model.PreguntasFrecuentes;
import cl.duoc.msvc_resenas.repository.PreguntasFrecuentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreguntasFrecuentesService {

    @Autowired
    private PreguntasFrecuentesRepository repository;

    private PreguntasFrecuentesDTO toDTO(PreguntasFrecuentes p) {
        PreguntasFrecuentesDTO dto = new PreguntasFrecuentesDTO();
        dto.setId(p.getId());
        dto.setPregunta(p.getPregunta());
        dto.setRespuesta(p.getRespuesta());
        dto.setCategoria(p.getCategoria());
        dto.setOrden(p.getOrden());
        dto.setActivo(p.isActivo());
        return dto;
    }

    private PreguntasFrecuentes toEntity(PreguntasFrecuentesDTO dto) {
        PreguntasFrecuentes p = new PreguntasFrecuentes();
        p.setId(dto.getId());
        p.setPregunta(dto.getPregunta());
        p.setRespuesta(dto.getRespuesta());
        p.setCategoria(dto.getCategoria());
        p.setOrden(dto.getOrden());
        p.setActivo(dto.isActivo());
        return p;
    }

    public List<PreguntasFrecuentesDTO> getAll() {
        return repository.findAllByOrderByOrdenAsc().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PreguntasFrecuentesDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Pregunta no encontrada con id: " + id));
    }

    public List<PreguntasFrecuentesDTO> getByCategoria(String categoria) {
        return repository.findByCategoria(categoria).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PreguntasFrecuentesDTO> getActivas() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PreguntasFrecuentesDTO create(PreguntasFrecuentesDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public PreguntasFrecuentesDTO update(int id, PreguntasFrecuentesDTO dto) {
        PreguntasFrecuentes p = toEntity(dto);
        p.setId(id);
        return toDTO(repository.save(p));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}