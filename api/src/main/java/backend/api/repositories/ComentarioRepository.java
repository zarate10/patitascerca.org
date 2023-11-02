package backend.api.repositories;

import backend.api.models.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    @Query("SELECT COUNT(c) FROM Comentario c WHERE c.post.id = :postId")
    Integer countComentariosByPostId(@Param("postId") Integer postId);

    @Query("SELECT c.id, c.comentario, c.usuario FROM Comentario c WHERE c.post.id = :postId")
    List<?> getComentariosByPost(@Param("postId") Integer postId);
}
