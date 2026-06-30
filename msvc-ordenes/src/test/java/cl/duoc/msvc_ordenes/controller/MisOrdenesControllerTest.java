package cl.duoc.msvc_ordenes.controller;

import cl.duoc.msvc_ordenes.dto.MisOrdenesDTO;
import cl.duoc.msvc_ordenes.exception.ResourceNotFoundException;
import cl.duoc.msvc_ordenes.service.MisOrdenesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MisOrdenesController.class)
public class MisOrdenesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MisOrdenesService service;

    @Autowired
    private ObjectMapper objectMapper;

    private MisOrdenesDTO ordenDTO;

    @BeforeEach
    void setUp() {
        ordenDTO = new MisOrdenesDTO();
        ordenDTO.setId(1);
        ordenDTO.setFechaCompra(LocalDateTime.now());
        ordenDTO.setTotal(25000);
        ordenDTO.setEstado("PAGADA");
        ordenDTO.setMetodoPago("TARJETA");
        ordenDTO.setNombreCliente("Juan González");
        ordenDTO.setNombreEvento("Festival Rock 2024");
    }

    @Test
    void getAll_debeRetornar200ConListaDeOrdenes() throws Exception {
        // Given
        when(service.getAll()).thenReturn(List.of(ordenDTO));

        // When & Then
        mockMvc.perform(get("/api/ordenes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PAGADA"))
                .andExpect(jsonPath("$[0].metodoPago").value("TARJETA"));
    }

    @Test
    void getById_debeRetornar200CuandoExiste() throws Exception {
        // Given
        when(service.getById(1)).thenReturn(ordenDTO);

        // When & Then
        mockMvc.perform(get("/api/ordenes/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.estado").value("PAGADA"));
    }

    @Test
    void getById_debeRetornar404CuandoNoExiste() throws Exception {
        // Given
        when(service.getById(99)).thenThrow(new ResourceNotFoundException("Orden no encontrada con id: 99"));

        // When & Then
        mockMvc.perform(get("/api/ordenes/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void create_debeRetornar201AlCrearOrden() throws Exception {
        // Given
        when(service.create(any(MisOrdenesDTO.class))).thenReturn(ordenDTO);

        // When & Then
        mockMvc.perform(post("/api/ordenes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.estado").value("PAGADA"));
    }

    @Test
    void update_debeRetornar200AlActualizar() throws Exception {
        // Given
        when(service.update(eq(1), any(MisOrdenesDTO.class))).thenReturn(ordenDTO);

        // When & Then
        mockMvc.perform(put("/api/ordenes/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordenDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.estado").value("PAGADA"));
    }

    @Test
    void delete_debeRetornar200AlEliminar() throws Exception {
        // Given
        doNothing().when(service).delete(1);

        // When & Then
        mockMvc.perform(delete("/api/ordenes/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Orden eliminada"));
    }

    @Test
    void getByEstado_debeRetornar200ConOrdenesPorEstado() throws Exception {
        // Given
        when(service.getByEstado("PAGADA")).thenReturn(List.of(ordenDTO));

        // When & Then
        mockMvc.perform(get("/api/ordenes/reporte/estado/PAGADA"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].estado").value("PAGADA"));
    }

    @Test
    void getByCliente_debeRetornar200ConOrdenesPorCliente() throws Exception {
        // Given
        when(service.getByCliente(1)).thenReturn(List.of(ordenDTO));

        // When & Then
        mockMvc.perform(get("/api/ordenes/reporte/cliente/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreCliente").value("Juan González"));
    }
}