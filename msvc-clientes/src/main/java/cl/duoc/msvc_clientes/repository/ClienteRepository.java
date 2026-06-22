package cl.duoc.msvc_clientes.repository;

import cl.duoc.msvc_clientes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByUsername(String username);
    Optional<Cliente> findByRut(String rut);
    List<Cliente> findByTipoCliente(String tipoCliente);
    List<Cliente> findByActivo(boolean activo);
    List<Cliente> findByCiudad(String ciudad);
}