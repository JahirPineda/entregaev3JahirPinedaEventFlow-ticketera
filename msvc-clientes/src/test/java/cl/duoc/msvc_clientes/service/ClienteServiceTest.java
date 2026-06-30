package cl.duoc.msvc_clientes.service;

import cl.duoc.msvc_clientes.dto.ClienteDTO;
import cl.duoc.msvc_clientes.exception.ResourceNotFoundException;
import cl.duoc.msvc_clientes.model.Cliente;
import cl.duoc.msvc_clientes.repository.ClienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteServiceTest {

    @Mock
    private ClienteRepository repository;

    @InjectMocks
    private ClienteService service;

    private Cliente cliente;
    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
        cliente.setId(1);
        cliente.setRut("12.345.678-9");
        cliente.setNombre("Juan");
        cliente.setApellido("González");
        cliente.setEmail("juan@mail.com");
        cliente.setTelefono("912345678");
        cliente.setDireccion("Av. Providencia 123");
        cliente.setCiudad("Santiago");
        cliente.setUsername("juangonzalez");
        cliente.setFechaRegistro(LocalDate.now());
        cliente.setActivo(true);
        cliente.setTipoCliente("REGULAR");

        clienteDTO = new ClienteDTO();
        clienteDTO.setId(1);
        clienteDTO.setRut("12.345.678-9");
        clienteDTO.setNombre("Juan");
        clienteDTO.setApellido("González");
        clienteDTO.setEmail("juan@mail.com");
        clienteDTO.setTelefono("912345678");
        clienteDTO.setDireccion("Av. Providencia 123");
        clienteDTO.setCiudad("Santiago");
        clienteDTO.setUsername("juangonzalez");
        clienteDTO.setFechaRegistro(LocalDate.now());
        clienteDTO.setActivo(true);
        clienteDTO.setTipoCliente("REGULAR");
    }

    @Test
    void getAll_debeRetornarListaDeClientes() {
        // Given
        when(repository.findAll()).thenReturn(List.of(cliente));

        // When
        List<ClienteDTO> resultado = service.getAll();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan", resultado.get(0).getNombre());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_debeRetornarClienteCuandoExiste() {
        // Given
        when(repository.findById(1)).thenReturn(Optional.of(cliente));

        // When
        ClienteDTO resultado = service.getById(1);

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
        assertEquals("Juan", resultado.getNombre());
        verify(repository, times(1)).findById(1);
    }

    @Test
    void getById_debeLanzarExcepcionCuandoNoExiste() {
        // Given
        when(repository.findById(99)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(ResourceNotFoundException.class, () -> service.getById(99));
        verify(repository, times(1)).findById(99);
    }

    @Test
    void create_debeGuardarYRetornarCliente() {
        // Given
        when(repository.save(any(Cliente.class))).thenReturn(cliente);

        // When
        ClienteDTO resultado = service.create(clienteDTO);

        // Then
        assertNotNull(resultado);
        assertEquals("Juan", resultado.getNombre());
        verify(repository, times(1)).save(any(Cliente.class));
    }

    @Test
    void delete_debeEliminarCliente() {
        // Given
        doNothing().when(repository).deleteById(1);

        // When
        service.delete(1);

        // Then
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void getActivos_debeRetornarSoloClientesActivos() {
        // Given
        when(repository.findByActivo(true)).thenReturn(List.of(cliente));

        // When
        List<ClienteDTO> resultado = service.getActivos();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertTrue(resultado.get(0).isActivo());
        verify(repository, times(1)).findByActivo(true);
    }

    @Test
    void getByTipo_debeRetornarClientesPorTipo() {
        // Given
        when(repository.findByTipoCliente("REGULAR")).thenReturn(List.of(cliente));

        // When
        List<ClienteDTO> resultado = service.getByTipo("REGULAR");

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("REGULAR", resultado.get(0).getTipoCliente());
        verify(repository, times(1)).findByTipoCliente("REGULAR");
    }
}