package backend.api.repositories;

import backend.api.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    @Query("SELECT COUNT(l.id) FROM Likes l WHERE l.post.id = :post_id AND l.usuario.id = :usuario_id")
    Boolean findByPostAndUsuario(@Param("post_id") Integer post_id, @Param("usuario_id") Integer usuario_id);
}
