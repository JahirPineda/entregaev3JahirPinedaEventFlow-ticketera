package cl.duoc.msvc_tickets.client;

import cl.duoc.msvc_tickets.dto.MisOrdenesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ordenesClient", url = "http://localhost:8086")
public interface OrdenesClient {

    @GetMapping("/api/ordenes/{id}")
    MisOrdenesDTO getOrdenById(@PathVariable int id);
}