package cl.duoc.msvc_exponentes.service;

import cl.duoc.msvc_exponentes.client.EventosClient;
import cl.duoc.msvc_exponentes.dto.EventosDTO;
import cl.duoc.msvc_exponentes.dto.ExponentesDTO;
import cl.duoc.msvc_exponentes.exception.ResourceNotFoundException;
import cl.duoc.msvc_exponentes.model.Exponentes;
import cl.duoc.msvc_exponentes.repository.ExponentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExponentesService {

    @Autowired
    private ExponentesRepository repository;

    @Autowired
    private EventosClient eventosClient;

    private ExponentesDTO toDTO(Exponentes e) {
        ExponentesDTO dto = new ExponentesDTO();
        dto.setId(e.getId());
        dto.setNombre(e.getNombre());
        dto.setApellido(e.getApellido());
        dto.setBiografia(e.getBiografia());
        dto.setNacionalidad(e.getNacionalidad());
        dto.setImagenUrl(e.getImagenUrl());
        dto.setRedesSociales(e.getRedesSociales());
        dto.setEventoId(e.getEventoId());
        try {
            EventosDTO evento = eventosClient.getEventoById(e.getEventoId());
            dto.setNombreEvento(evento.getNombre());
        } catch (Exception ex) {
            dto.setNombreEvento("Evento " + e.getEventoId());
        }
        return dto;
    }

    private Exponentes toEntity(ExponentesDTO dto) {
        Exponentes e = new Exponentes();
        e.setId(dto.getId());
        e.setNombre(dto.getNombre());
        e.setApellido(dto.getApellido());
        e.setBiografia(dto.getBiografia());
        e.setNacionalidad(dto.getNacionalidad());
        e.setImagenUrl(dto.getImagenUrl());
        e.setRedesSociales(dto.getRedesSociales());
        e.setEventoId(dto.getEventoId());
        return e;
    }

    public List<ExponentesDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ExponentesDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Exponente no encontrado con id: " + id));
    }

    public List<ExponentesDTO> getByEvento(int eventoId) {
        return repository.findByEventoId(eventoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ExponentesDTO> getByNacionalidad(String nacionalidad) {
        return repository.findByNacionalidad(nacionalidad).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ExponentesDTO> getByNombre(String nombre) {
        return repository.findByNombreContainingIgnoreCase(nombre).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ExponentesDTO> getByApellido(String apellido) {
        return repository.findByApellidoContainingIgnoreCase(apellido).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ExponentesDTO create(ExponentesDTO dto) {
        try {
            EventosDTO evento = eventosClient.getEventoById(dto.getEventoId());
            if (evento == null) {
                throw new ResourceNotFoundException("Evento no encontrado con id: " + dto.getEventoId());
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Evento no encontrado con id: " + dto.getEventoId());
        }
        return toDTO(repository.save(toEntity(dto)));
    }

    public ExponentesDTO update(int id, ExponentesDTO dto) {
        Exponentes e = toEntity(dto);
        e.setId(id);
        return toDTO(repository.save(e));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}