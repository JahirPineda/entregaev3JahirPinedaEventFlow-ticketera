package cl.duoc.msvc_resenas.repository;

import cl.duoc.msvc_resenas.model.Resenas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResenasRepository extends JpaRepository<Resenas, Integer> {
    List<Resenas> findByClienteId(int clienteId);
    List<Resenas> findByEventoId(int eventoId);
    List<Resenas> findByPuntuacion(int puntuacion);
    List<Resenas> findByPuntuacionGreaterThanEqual(int puntuacion);
}