package cl.duoc.msvc_ordenes.service;

import cl.duoc.msvc_ordenes.client.ClientesClient;
import cl.duoc.msvc_ordenes.client.EventosClient;
import cl.duoc.msvc_ordenes.dto.ClientesDTO;
import cl.duoc.msvc_ordenes.dto.EventosDTO;
import cl.duoc.msvc_ordenes.dto.MisOrdenesDTO;
import cl.duoc.msvc_ordenes.exception.ResourceNotFoundException;
import cl.duoc.msvc_ordenes.model.MisOrdenes;
import cl.duoc.msvc_ordenes.repository.MisOrdenesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MisOrdenesService {

    @Autowired
    private MisOrdenesRepository repository;

    @Autowired
    private ClientesClient clientesClient;

    @Autowired
    private EventosClient eventosClient;

    private MisOrdenesDTO toDTO(MisOrdenes o) {
        MisOrdenesDTO dto = new MisOrdenesDTO();
        dto.setId(o.getId());
        dto.setFechaCompra(o.getFechaCompra());
        dto.setTotal(o.getTotal());
        dto.setEstado(o.getEstado());
        dto.setMetodoPago(o.getMetodoPago());
        try {
            ClientesDTO cliente = clientesClient.getClienteById(o.getClienteId());
            dto.setNombreCliente(cliente.getNombre() + " " + cliente.getApellido());
        } catch (Exception e) {
            dto.setNombreCliente("Cliente " + o.getClienteId());
        }
        try {
            EventosDTO evento = eventosClient.getEventoById(o.getEventoId());
            dto.setNombreEvento(evento.getNombre());
        } catch (Exception e) {
            dto.setNombreEvento("Evento " + o.getEventoId());
        }
        return dto;
    }

    private MisOrdenes toEntity(MisOrdenesDTO dto) {
        MisOrdenes o = new MisOrdenes();
        o.setId(dto.getId());
        o.setFechaCompra(dto.getFechaCompra());
        o.setTotal(dto.getTotal());
        o.setEstado(dto.getEstado());
        o.setMetodoPago(dto.getMetodoPago());
        return o;
    }

    public List<MisOrdenesDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MisOrdenesDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Orden no encontrada con id: " + id));
    }

    public List<MisOrdenesDTO> getByCliente(int clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MisOrdenesDTO> getByEvento(int eventoId) {
        return repository.findByEventoId(eventoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MisOrdenesDTO> getByEstado(String estado) {
        return repository.findByEstado(estado).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MisOrdenesDTO> getByMetodoPago(String metodoPago) {
        return repository.findByMetodoPago(metodoPago).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<MisOrdenesDTO> getByTotalMinimo(double total) {
        return repository.findByTotalGreaterThanEqual(total).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public MisOrdenesDTO create(MisOrdenesDTO dto) {
        MisOrdenes o = toEntity(dto);
        o.setFechaCompra(LocalDateTime.now());
        o.setEstado("PENDIENTE");
        return toDTO(repository.save(o));
    }

    public MisOrdenesDTO update(int id, MisOrdenesDTO dto) {
        MisOrdenes o = toEntity(dto);
        o.setId(id);
        return toDTO(repository.save(o));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}