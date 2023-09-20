package backend.api.repositories;

import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, Integer> {
    @Query("SELECT s.seguidor FROM Seguidor s WHERE s.seguido.id = :id")
    List<Usuario> findSeguidores(@Param("id") Integer id);

    @Query("SELECT s.seguido FROM Seguidor s WHERE s.seguidor.id = :id")
    List<Usuario> findSeguidos(@Param("id") Integer id);

    @Query("SELECT s FROM Seguidor s WHERE s.seguidor.id = :idSeguidor AND s.seguido.id = :idSeguido")
    Seguidor getRelacion(@Param("idSeguidor") Integer idSeguidor, @Param("idSeguido") Integer idSeguido);

    @Query("SELECT COUNT(s.id) FROM Seguidor s WHERE s.seguidor.id = :idSeguidor AND s.seguido.id = :idSeguido")
    Integer existeRelacion(@Param("idSeguidor") Integer idSeguidor, @Param("idSeguido") Integer idSeguido);
}
