package cl.duoc.msvc_clientes.service;

import cl.duoc.msvc_clientes.dto.ClienteDTO;
import cl.duoc.msvc_clientes.exception.ResourceNotFoundException;
import cl.duoc.msvc_clientes.model.Cliente;
import cl.duoc.msvc_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    private ClienteDTO toDTO(Cliente cliente) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(cliente.getId());
        dto.setRut(cliente.getRut());
        dto.setNombre(cliente.getNombre());
        dto.setApellido(cliente.getApellido());
        dto.setEmail(cliente.getEmail());
        dto.setTelefono(cliente.getTelefono());
        dto.setDireccion(cliente.getDireccion());
        dto.setCiudad(cliente.getCiudad());
        dto.setUsername(cliente.getUsername());
        dto.setFechaRegistro(cliente.getFechaRegistro());
        dto.setActivo(cliente.isActivo());
        dto.setTipoCliente(cliente.getTipoCliente());
        return dto;
    }

    private Cliente toEntity(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setId(dto.getId());
        cliente.setRut(dto.getRut());
        cliente.setNombre(dto.getNombre());
        cliente.setApellido(dto.getApellido());
        cliente.setEmail(dto.getEmail());
        cliente.setTelefono(dto.getTelefono());
        cliente.setDireccion(dto.getDireccion());
        cliente.setCiudad(dto.getCiudad());
        cliente.setUsername(dto.getUsername());
        cliente.setPassword(dto.getPassword());
        cliente.setTipoCliente(dto.getTipoCliente());
        return cliente;
    }

    public List<ClienteDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO getById(int id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con id: " + id));
    }

    public Optional<ClienteDTO> getByEmail(String email) {
        return repository.findByEmail(email).map(this::toDTO);
    }

    public Optional<ClienteDTO> getByRut(String rut) {
        return repository.findByRut(rut).map(this::toDTO);
    }

    public List<ClienteDTO> getByTipo(String tipoCliente) {
        return repository.findByTipoCliente(tipoCliente).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ClienteDTO> getActivos() {
        return repository.findByActivo(true).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public List<ClienteDTO> getByCiudad(String ciudad) {
        return repository.findByCiudad(ciudad).stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ClienteDTO create(ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        cliente.setFechaRegistro(LocalDate.now());
        cliente.setActivo(true);
        return toDTO(repository.save(cliente));
    }

    public ClienteDTO update(int id, ClienteDTO dto) {
        Cliente cliente = toEntity(dto);
        cliente.setId(id);
        return toDTO(repository.save(cliente));
    }

    public void delete(int id) {
        repository.deleteById(id);
    }
}