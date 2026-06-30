package cl.duoc.msvc_tickets.client;

import cl.duoc.msvc_tickets.dto.ClientesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientesClientTickets", url = "http://localhost:8081")
public interface ClientesClient {

    @GetMapping("/api/clientes/{id}")
    ClientesDTO getClienteById(@PathVariable int id);
}