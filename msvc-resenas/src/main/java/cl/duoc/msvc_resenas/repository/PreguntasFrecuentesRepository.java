package cl.duoc.msvc_resenas.repository;

import cl.duoc.msvc_resenas.model.PreguntasFrecuentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreguntasFrecuentesRepository extends JpaRepository<PreguntasFrecuentes, Integer> {
    List<PreguntasFrecuentes> findByCategoria(String categoria);
    List<PreguntasFrecuentes> findByActivo(boolean activo);
    List<PreguntasFrecuentes> findAllByOrderByOrdenAsc();
}