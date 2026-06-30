package cl.duoc.msvc_recintos.repository;

import cl.duoc.msvc_recintos.model.Recintos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecintosRepository extends JpaRepository<Recintos, Integer> {
    List<Recintos> findByCiudad(String ciudad);
    List<Recintos> findByActivo(boolean activo);
    List<Recintos> findByCapacidadGreaterThanEqual(int capacidad);
    List<Recintos> findByNombreContainingIgnoreCase(String nombre);
}