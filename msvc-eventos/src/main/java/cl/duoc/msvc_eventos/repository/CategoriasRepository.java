package cl.duoc.msvc_eventos.repository;

import cl.duoc.msvc_eventos.model.Categorias;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriasRepository extends JpaRepository<Categorias, Integer> {
    List<Categorias> findByActivo(boolean activo);
    List<Categorias> findByNombreContainingIgnoreCase(String nombre);
}