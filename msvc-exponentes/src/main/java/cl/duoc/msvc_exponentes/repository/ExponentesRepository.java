package cl.duoc.msvc_exponentes.repository;

import cl.duoc.msvc_exponentes.model.Exponentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExponentesRepository extends JpaRepository<Exponentes, Integer> {
    List<Exponentes> findByEventoId(int eventoId);
    List<Exponentes> findByNacionalidad(String nacionalidad);
    List<Exponentes> findByNombreContainingIgnoreCase(String nombre);
    List<Exponentes> findByApellidoContainingIgnoreCase(String apellido);
}