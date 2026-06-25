package cl.duoc.msvc_ordenes.repository;

import cl.duoc.msvc_ordenes.model.MisOrdenes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MisOrdenesRepository extends JpaRepository<MisOrdenes, Integer> {
    List<MisOrdenes> findByClienteId(int clienteId);
    List<MisOrdenes> findByEventoId(int eventoId);
    List<MisOrdenes> findByEstado(String estado);
    List<MisOrdenes> findByMetodoPago(String metodoPago);
    List<MisOrdenes> findByTotalGreaterThanEqual(double total);
}