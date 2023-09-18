package backend.api.repositories;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SeguidorRepository extends JpaRepository<Seguidor, Integer> {
    @Query("SELECT u FROM Usuario u JOIN Seguidor s ON u.id = s.usuarioSeguido.id WHERE s.usuarioSeguido.id = :id")
    List<Usuario> findSeguidores(@Param("id") Integer id);

    @Query("SELECT u FROM Usuario u JOIN Seguidor s ON u.id = s.seguidor WHERE s.seguidor = :id")
    List<Seguidor> findSeguidos(@Param("id") Integer id);

    @Query("SELECT s FROM Seguidor s WHERE s.seguidor.id = :idSeguidor AND s.usuarioSeguido.id = :idSeguido")
    Seguidor findRelacion(@Param("idSeguidor") Integer idSeguidor, @Param("idSeguido") Integer idSeguido);
}
