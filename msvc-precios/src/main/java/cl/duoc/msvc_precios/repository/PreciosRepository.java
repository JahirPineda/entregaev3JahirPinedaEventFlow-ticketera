package cl.duoc.msvc_precios.repository;

import cl.duoc.msvc_precios.model.Precios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreciosRepository extends JpaRepository<Precios, Integer> {
    List<Precios> findByEventoId(int eventoId);
    List<Precios> findByTipoEntrada(String tipoEntrada);
    List<Precios> findByActivo(boolean activo);
    List<Precios> findByValorLessThanEqual(double valor);
    List<Precios> findByStockGreaterThan(int stock);
}