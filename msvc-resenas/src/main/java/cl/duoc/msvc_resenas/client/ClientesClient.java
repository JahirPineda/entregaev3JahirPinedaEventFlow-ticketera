package cl.duoc.msvc_resenas.client;

import cl.duoc.msvc_resenas.dto.ClientesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "clientesClientResenas", url = "http://localhost:8081")
public interface ClientesClient {

    @GetMapping("/api/clientes/{id}")
    ClientesDTO getClienteById(@PathVariable int id);
}