package backend.api.repositories;

import backend.api.models.Likes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikesRepository extends JpaRepository<Likes, Integer> {
    @Query("SELECT COUNT(l.id) FROM Likes l WHERE l.post.id = :post_id AND l.usuario.id = :usuario_id")
    Integer likesUserPost(@Param("post_id") Integer post_id, @Param("usuario_id") Integer usuario_id);

    @Query("SELECT l FROM Likes l WHERE l.post.id = :post_id AND l.usuario.id = :usuario_id")
    Likes getPostLiked(@Param("post_id") Integer post_id, @Param("usuario_id") Integer usuario_id);

    @Query("SELECT COUNT(l) FROM Likes l WHERE l.post.id = :postId")
    Integer countLikesByPostId(@Param("postId") Integer postId);

    @Query("SELECT l.post.id FROM Likes l WHERE l.usuario.id = :usuario_id")
    List<Integer> getPostLikedByUser(@Param("usuario_id") Integer usuario_id);
}
