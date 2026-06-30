package cl.duoc.msvc_ordenes.service;

import cl.duoc.msvc_ordenes.client.ClientesClient;
import cl.duoc.msvc_ordenes.client.EventosClient;
import cl.duoc.msvc_ordenes.dto.ClientesDTO;
import cl.duoc.msvc_ordenes.dto.EventosDTO;
import cl.duoc.msvc_ordenes.dto.MisOrdenesDTO;
import cl.duoc.msvc_ordenes.exception.ResourceNotFoundException;
import cl.duoc.msvc_ordenes.model.MisOrdenes;
import cl.duoc.msvc_ordenes.repository.MisOrdenesRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MisOrdenesServiceTest {

    @Mock
    private MisOrdenesRepository repository;

    @Mock
    private ClientesClient clientesClient;

    @Mock
    private EventosClient eventosClient;

    @InjectMocks
    private MisOrdenesService service;

    private MisOrdenes orden;
    private MisOrdenesDTO ordenDTO;
    private ClientesDTO clientesDTO;
    private EventosDTO eventosDTO;

    @BeforeEach
    void setUp() {
        orden = new MisOrdenes();
        orden.setId(1);
        orden.setFechaCompra(LocalDateTime.now());
        orden.setTotal(25000);
        orden.setEstado("PAGADA");
        orden.setMetodoPago("TARJETA");
        orden.setClienteId(1);
        orden.setEventoId(1);

        ordenDTO = new MisOrdenesDTO();
        ordenDTO.setId(1);
        ordenDTO.setFechaCompra(LocalDateTime.now());
        ordenDTO.setTotal(25000);
        ordenDTO.setEstado("PAGADA");
        ordenDTO.setMetodoPago("TARJETA");

        clientesDTO = new ClientesDTO();
        clientesDTO.setId(1);
        clientesDTO.setNombre("Juan");
        clientesDTO.setApellido("González");

        eventosDTO = new EventosDTO();
        eventosDTO.setId(1);
        eventosDTO.setNombre("Festival Rock 2024");
    }

    @Test
    void getAll_debeRetornarListaDeOrdenes() {
        // Given
        when(repository.findAll()).thenReturn(List.of(orden));
        when(clientesClient.getClienteById(1)).thenReturn(clientesDTO);
        when(eventosClient.getEventoById(1)).thenReturn(eventosDTO);

        // When
        List<MisOrdenesDTO> resultado = service.getAll();

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Juan González", resultado.get(0).getNombreCliente());
        assertEquals("Festival Rock 2024", resultado.get(0).getNombreEvento());
        verify(repository, times(1)).findAll();
    }

    @Test
    void getById_debeRetornarOrdenCuandoExiste() {
        // Given
        when(repository.findById(1)).thenReturn(Optional.of(orden));
        when(clientesClient.getClienteById(1)).thenReturn(clientesDTO);
        when(eventosClient.getEventoById(1)).thenReturn(eventosDTO);

        // When
        MisOrdenesDTO resultado = service.getById(1);

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.getId());
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
    void create_debeGuardarYRetornarOrden() {
        // Given
        when(repository.save(any(MisOrdenes.class))).thenReturn(orden);
        when(clientesClient.getClienteById(anyInt())).thenReturn(clientesDTO);
        when(eventosClient.getEventoById(anyInt())).thenReturn(eventosDTO);

        // When
        MisOrdenesDTO resultado = service.create(ordenDTO);

        // Then
        assertNotNull(resultado);
        verify(repository, times(1)).save(any(MisOrdenes.class));
    }

    @Test
    void delete_debeEliminarOrden() {
        // Given
        doNothing().when(repository).deleteById(1);

        // When
        service.delete(1);

        // Then
        verify(repository, times(1)).deleteById(1);
    }

    @Test
    void getByEstado_debeRetornarOrdenesPorEstado() {
        // Given
        when(repository.findByEstado("PAGADA")).thenReturn(List.of(orden));
        when(clientesClient.getClienteById(1)).thenReturn(clientesDTO);
        when(eventosClient.getEventoById(1)).thenReturn(eventosDTO);

        // When
        List<MisOrdenesDTO> resultado = service.getByEstado("PAGADA");

        // Then
        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("PAGADA", resultado.get(0).getEstado());
        verify(repository, times(1)).findByEstado("PAGADA");
    }

    @Test
    void getAll_debeUsarFallbackCuandoClienteFalla() {
        // Given
        when(repository.findAll()).thenReturn(List.of(orden));
        when(clientesClient.getClienteById(1)).thenThrow(new RuntimeException("Servicio no disponible"));
        when(eventosClient.getEventoById(1)).thenReturn(eventosDTO);

        // When
        List<MisOrdenesDTO> resultado = service.getAll();

        // Then
        assertNotNull(resultado);
        assertEquals("Cliente 1", resultado.get(0).getNombreCliente());
    }
}