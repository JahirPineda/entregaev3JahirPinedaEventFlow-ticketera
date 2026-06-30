package cl.duoc.msvc_clientes.controller;

import cl.duoc.msvc_clientes.dto.ClienteDTO;
import cl.duoc.msvc_clientes.exception.ResourceNotFoundException;
import cl.duoc.msvc_clientes.service.ClienteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ClienteController.class)
public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService service;

    @Autowired
    private ObjectMapper objectMapper;

    private ClienteDTO clienteDTO;

    @BeforeEach
    void setUp() {
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
        clienteDTO.setPassword("pass123");
        clienteDTO.setFechaRegistro(LocalDate.now());
        clienteDTO.setActivo(true);
        clienteDTO.setTipoCliente("REGULAR");
    }

    @Test
    void getAll_debeRetornar200ConListaDeClientes() throws Exception {
        when(service.getAll()).thenReturn(List.of(clienteDTO));

        mockMvc.perform(get("/api/clientes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Juan"))
                .andExpect(jsonPath("$[0].rut").value("12.345.678-9"));
    }

    @Test
    void getById_debeRetornar200CuandoExiste() throws Exception {
        when(service.getById(1)).thenReturn(clienteDTO);

        mockMvc.perform(get("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getById_debeRetornar404CuandoNoExiste() throws Exception {
        when(service.getById(99)).thenThrow(new ResourceNotFoundException("Cliente no encontrado con id: 99"));

        mockMvc.perform(get("/api/clientes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_debeRetornar201AlCrearCliente() throws Exception {
        when(service.create(any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(post("/api/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void update_debeRetornar200AlActualizar() throws Exception {
        when(service.update(eq(1), any(ClienteDTO.class))).thenReturn(clienteDTO);

        mockMvc.perform(put("/api/clientes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(clienteDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Juan"));
    }

    @Test
    void delete_debeRetornar200AlEliminar() throws Exception {
        doNothing().when(service).delete(1);

        mockMvc.perform(delete("/api/clientes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Cliente eliminado"));
    }

    @Test
    void getActivos_debeRetornar200ConClientesActivos() throws Exception {
        when(service.getActivos()).thenReturn(List.of(clienteDTO));

        mockMvc.perform(get("/api/clientes/reporte/activos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].activo").value(true));
    }

    @Test
    void getByEmail_debeRetornar200CuandoExiste() throws Exception {
        when(service.getByEmail("juan@mail.com")).thenReturn(Optional.of(clienteDTO));

        mockMvc.perform(get("/api/clientes/reporte/email/juan@mail.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("juan@mail.com"));
    }
}