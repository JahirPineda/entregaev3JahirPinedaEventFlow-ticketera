package cl.duoc.msvc_exponentes.client;

import cl.duoc.msvc_exponentes.dto.EventosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eventosClient", url = "http://localhost:8082")
public interface EventosClient {

    @GetMapping("/api/eventos/{id}")
    EventosDTO getEventoById(@PathVariable int id);
}