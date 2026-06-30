package cl.duoc.msvc_eventos.repository;

import cl.duoc.msvc_eventos.model.Eventos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EventosRepository extends JpaRepository<Eventos, Integer> {
    List<Eventos> findByFecha(LocalDate fecha);
    List<Eventos> findByCategoriaId(int categoriaId);
    List<Eventos> findByRecintoId(int recintoId);
    List<Eventos> findByActivo(boolean activo);
    List<Eventos> findByFechaBetween(LocalDate inicio, LocalDate fin);
    List<Eventos> findByNombreContainingIgnoreCase(String nombre);
}