package cl.duoc.msvc_recintos.service;

import cl.duoc.msvc_recintos.dto.RecintosDTO;
import cl.duoc.msvc_recintos.exception.ResourceNotFoundException;
import cl.duoc.msvc_recintos.model.Recintos;
import cl.duoc.msvc_recintos.repository.RecintosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecintosService {

    @Autowired
    private RecintosRepository repository;

    private RecintosDTO toDTO(Recintos r) {
        RecintosDTO dto = new RecintosDTO();
        dto.setId(r.getId());
        dto.setNombre(r.getNombre());
        dto.setDireccion(r.getDireccion());
        dto.setCiudad(r.getCiudad());
        dto.setCapacidad(r.getCapacidad());
        dto.setImagenUrl(r.getImagenUrl());
        dto.setActivo(r.isActivo());
        return dto;
    }

    private Recintos toEntity(RecintosDTO dto) {
        Recintos r = new Recintos();
        r.setId(dto.getId());
        r.setNombre(dto.getNombre());
        r.setDireccion(dto.getDireccion());
        r.setCiudad(dto.getCiudad());
        r.setCapacidad(dto.getCapacidad());
        r.setImagenUrl(dto.getImagenUrl());
        r.setActivo(dto.isActivo());
        return r;
    }

    public List<RecintosDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RecintosDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Recinto no encontrado con id: " + id));
    }

    public List<RecintosDTO> getByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<RecintosDTO> getActivos() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<RecintosDTO> getByCapacidadMinima(int capacidad) {
        return repository.findByCapacidadGreaterThanEqual(capacidad).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<RecintosDTO> getByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public RecintosDTO create(RecintosDTO dto) {
        return toDTO(repository.save(toEntity(dto)));
    }

    public RecintosDTO update(int id, RecintosDTO dto) {
        Recintos r = toEntity(dto);
        r.setId(id);
        return toDTO(repository.save(r));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}