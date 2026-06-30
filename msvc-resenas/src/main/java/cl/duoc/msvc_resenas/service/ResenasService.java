package cl.duoc.msvc_resenas.service;

import cl.duoc.msvc_resenas.client.ClientesClient;
import cl.duoc.msvc_resenas.client.EventosClient;
import cl.duoc.msvc_resenas.dto.ClientesDTO;
import cl.duoc.msvc_resenas.dto.EventosDTO;
import cl.duoc.msvc_resenas.dto.ResenasDTO;
import cl.duoc.msvc_resenas.exception.ResourceNotFoundException;
import cl.duoc.msvc_resenas.model.Resenas;
import cl.duoc.msvc_resenas.repository.ResenasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResenasService {

    @Autowired
    private ResenasRepository repository;

    @Autowired
    private ClientesClient clientesClient;

    @Autowired
    private EventosClient eventosClient;

    private ResenasDTO toDTO(Resenas r) {
        ResenasDTO dto = new ResenasDTO();
        dto.setId(r.getId());
        dto.setPuntuacion(r.getPuntuacion());
        dto.setComentario(r.getComentario());
        dto.setFecha(r.getFecha());
        dto.setClienteId(r.getClienteId());
        dto.setEventoId(r.getEventoId());
        try {
            ClientesDTO cliente = clientesClient.getClienteById(r.getClienteId());
            dto.setNombreCliente(cliente.getNombre() + " " + cliente.getApellido());
        } catch (Exception e) {
            dto.setNombreCliente("Cliente " + r.getClienteId());
        }
        try {
            EventosDTO evento = eventosClient.getEventoById(r.getEventoId());
            dto.setNombreEvento(evento.getNombre());
        } catch (Exception e) {
            dto.setNombreEvento("Evento " + r.getEventoId());
        }
        return dto;
    }

    private Resenas toEntity(ResenasDTO dto) {
        Resenas r = new Resenas();
        r.setId(dto.getId());
        r.setPuntuacion(dto.getPuntuacion());
        r.setComentario(dto.getComentario());
        r.setFecha(dto.getFecha());
        r.setClienteId(dto.getClienteId());
        r.setEventoId(dto.getEventoId());
        return r;
    }

    public List<ResenasDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ResenasDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Reseña no encontrada con id: " + id));
    }

    public List<ResenasDTO> getByCliente(int clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ResenasDTO> getByEvento(int eventoId) {
        return repository.findByEventoId(eventoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ResenasDTO> getByPuntuacion(int puntuacion) {
        return repository.findByPuntuacion(puntuacion).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ResenasDTO> getByPuntuacionMinima(int puntuacion) {
        return repository.findByPuntuacionGreaterThanEqual(puntuacion).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ResenasDTO create(ResenasDTO dto) {
        Resenas r = toEntity(dto);
        r.setFecha(LocalDateTime.now());
        return toDTO(repository.save(r));
    }

    public ResenasDTO update(int id, ResenasDTO dto) {
        Resenas r = toEntity(dto);
        r.setId(id);
        return toDTO(repository.save(r));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}