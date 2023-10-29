package backend.api.repositories;

import backend.api.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    @Query("SELECT u.id FROM Usuario u WHERE u.username = :username")
    Integer findIdByUsername(@Param("username") String username);

    @Query("SELECT COUNT(u.id) FROM Usuario u WHERE u.username = :username or u.email = :email")
    Integer existUsername(@Param("username") String username, @Param("email") String email);
}
