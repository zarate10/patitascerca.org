package backend.api.repositories;

import backend.api.models.Imagen;
import backend.api.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepository extends JpaRepository<Imagen, Integer> {
    @Query("SELECT COUNT(i.id) FROM Imagen i WHERE i.post.id = :post_id")
    Integer countImgPost(@Param("post_id") Integer post_id);

    @Query("SELECT i FROM Imagen i WHERE name LIKE :post_name%")
    List<Imagen> getAllPostImgByName(@Param("post_name") String post_name);

    @Query("SELECT i FROM Imagen i WHERE i.name = :img_name")
    Imagen getPostImgByName(@Param("img_name") String img_name);
}
