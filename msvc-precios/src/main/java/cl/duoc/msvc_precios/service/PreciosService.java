package cl.duoc.msvc_precios.service;

import cl.duoc.msvc_precios.client.EventosClient;
import cl.duoc.msvc_precios.dto.EventosDTO;
import cl.duoc.msvc_precios.dto.PreciosDTO;
import cl.duoc.msvc_precios.exception.ResourceNotFoundException;
import cl.duoc.msvc_precios.model.Precios;
import cl.duoc.msvc_precios.repository.PreciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PreciosService {

    @Autowired
    private PreciosRepository repository;

    @Autowired
    private EventosClient eventosClient;

    private PreciosDTO toDTO(Precios p) {
        PreciosDTO dto = new PreciosDTO();
        dto.setId(p.getId());
        dto.setTipoEntrada(p.getTipoEntrada());
        dto.setValor(p.getValor());
        dto.setStock(p.getStock());
        dto.setActivo(p.isActivo());
        dto.setEventoId(p.getEventoId());
        try {
            EventosDTO evento = eventosClient.getEventoById(p.getEventoId());
            dto.setNombreEvento(evento.getNombre());
        } catch (Exception e) {
            dto.setNombreEvento("Evento " + p.getEventoId());
        }
        return dto;
    }

    private Precios toEntity(PreciosDTO dto) {
        Precios p = new Precios();
        p.setId(dto.getId());
        p.setTipoEntrada(dto.getTipoEntrada());
        p.setValor(dto.getValor());
        p.setStock(dto.getStock());
        p.setActivo(dto.isActivo());
        p.setEventoId(dto.getEventoId());
        return p;
    }

    public List<PreciosDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PreciosDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Precio no encontrado con id: " + id));
    }

    public List<PreciosDTO> getByEvento(int eventoId) {
        return repository.findByEventoId(eventoId).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PreciosDTO> getByTipoEntrada(String tipoEntrada) {
        return repository.findByTipoEntrada(tipoEntrada).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PreciosDTO> getActivos() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PreciosDTO> getByValorMaximo(double valor) {
        return repository.findByValorLessThanEqual(valor).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<PreciosDTO> getConStock() {
        return repository.findByStockGreaterThan(0).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public PreciosDTO create(PreciosDTO dto) {
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

    public PreciosDTO update(int id, PreciosDTO dto) {
        Precios p = toEntity(dto);
        p.setId(id);
        return toDTO(repository.save(p));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}