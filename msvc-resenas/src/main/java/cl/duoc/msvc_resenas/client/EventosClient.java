package cl.duoc.msvc_resenas.client;

import cl.duoc.msvc_resenas.dto.EventosDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "eventosClientResenas", url = "http://localhost:8082")
public interface EventosClient {

    @GetMapping("/api/eventos/{id}")
    EventosDTO getEventoById(@PathVariable int id);
}