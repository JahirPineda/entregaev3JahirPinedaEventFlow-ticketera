package cl.duoc.msvc_tickets.service;

import cl.duoc.msvc_tickets.client.ClientesClient;
import cl.duoc.msvc_tickets.client.EventosClient;
import cl.duoc.msvc_tickets.client.OrdenesClient;
import cl.duoc.msvc_tickets.dto.ClientesDTO;
import cl.duoc.msvc_tickets.dto.EventosDTO;
import cl.duoc.msvc_tickets.dto.MisOrdenesDTO;
import cl.duoc.msvc_tickets.dto.TicketsGeneradosDTO;
import cl.duoc.msvc_tickets.exception.ResourceNotFoundException;
import cl.duoc.msvc_tickets.model.TicketsGenerados;
import cl.duoc.msvc_tickets.repository.TicketsGeneradosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TicketsGeneradosService {

    @Autowired
    private TicketsGeneradosRepository repository;

    @Autowired
    private OrdenesClient ordenesClient;

    @Autowired
    private ClientesClient clientesClient;

    @Autowired
    private EventosClient eventosClient;

    private TicketsGeneradosDTO toDTO(TicketsGenerados t) {
        TicketsGeneradosDTO dto = new TicketsGeneradosDTO();
        dto.setId(t.getId());
        dto.setCodigoQR(t.getCodigoQR());
        dto.setFechaEmision(t.getFechaEmision());
        dto.setUsado(t.isUsado());
        dto.setTipoEntrada(t.getTipoEntrada());
        dto.setOrdenId(t.getOrdenId());
        dto.setClienteId(t.getClienteId());
        dto.setEventoId(t.getEventoId());
        try {
            ClientesDTO cliente = clientesClient.getClienteById(t.getClienteId());
            dto.setNombreCliente(cliente.getNombre() + " " + cliente.getApellido());
        } catch (Exception e) {
            dto.setNombreCliente("Cliente " + t.getClienteId());
        }
        try {
            EventosDTO evento = eventosClient.getEventoById(t.getEventoId());
            dto.setNombreEvento(evento.getNombre());
        } catch (Exception e) {
            dto.setNombreEvento("Evento " + t.getEventoId());
        }
        return dto;
    }

    private TicketsGenerados toEntity(TicketsGeneradosDTO dto) {
        TicketsGenerados t = new TicketsGenerados();
        t.setId(dto.getId());
        t.setCodigoQR(dto.getCodigoQR());
        t.setFechaEmision(dto.getFechaEmision());
        t.setUsado(dto.isUsado());
        t.setTipoEntrada(dto.getTipoEntrada());
        t.setOrdenId(dto.getOrdenId());
        t.setClienteId(dto.getClienteId());
        t.setEventoId(dto.getEventoId());
        return t;
    }

    public List<TicketsGeneradosDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TicketsGeneradosDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con id: " + id));
    }

    public List<TicketsGeneradosDTO> getByCliente(int clienteId) {
        return repository.findByClienteId(clienteId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TicketsGeneradosDTO> getByEvento(int eventoId) {
        return repository.findByEventoId(eventoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TicketsGeneradosDTO> getByOrden(int ordenId) {
        return repository.findByOrdenId(ordenId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TicketsGeneradosDTO getByCodigoQR(String codigoQR) {
        return repository.findByCodigoQR(codigoQR)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Ticket no encontrado con QR: " + codigoQR));
    }

    public List<TicketsGeneradosDTO> getUsados() {
        return repository.findByUsado(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TicketsGeneradosDTO> getNoUsados() {
        return repository.findByUsado(false).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<TicketsGeneradosDTO> getByTipoEntrada(String tipoEntrada) {
        return repository.findByTipoEntrada(tipoEntrada).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public TicketsGeneradosDTO create(TicketsGeneradosDTO dto) {
        try {
            MisOrdenesDTO orden = ordenesClient.getOrdenById(dto.getOrdenId());
            if (orden == null) {
                throw new ResourceNotFoundException("Orden no encontrada con id: " + dto.getOrdenId());
            }
        } catch (ResourceNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new ResourceNotFoundException("Orden no encontrada con id: " + dto.getOrdenId());
        }
        TicketsGenerados t = toEntity(dto);
        t.setCodigoQR(UUID.randomUUID().toString());
        t.setFechaEmision(LocalDateTime.now());
        t.setUsado(false);
        return toDTO(repository.save(t));
    }

    public TicketsGeneradosDTO update(int id, TicketsGeneradosDTO dto) {
        TicketsGenerados t = toEntity(dto);
        t.setId(id);
        return toDTO(repository.save(t));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}