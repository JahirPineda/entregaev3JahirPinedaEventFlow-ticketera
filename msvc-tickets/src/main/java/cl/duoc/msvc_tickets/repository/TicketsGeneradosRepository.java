package cl.duoc.msvc_tickets.repository;

import cl.duoc.msvc_tickets.model.TicketsGenerados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketsGeneradosRepository extends JpaRepository<TicketsGenerados, Integer> {
    List<TicketsGenerados> findByClienteId(int clienteId);
    List<TicketsGenerados> findByEventoId(int eventoId);
    List<TicketsGenerados> findByOrdenId(int ordenId);
    Optional<TicketsGenerados> findByCodigoQR(String codigoQR);
    List<TicketsGenerados> findByUsado(boolean usado);
    List<TicketsGenerados> findByTipoEntrada(String tipoEntrada);
}