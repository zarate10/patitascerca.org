package backend.api.services;

import backend.api.models.Likes;
import backend.api.repositories.LikesRepository;
import backend.api.repositories.PostRepository;
import backend.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Service
public class LikesService {
    @Autowired
    private LikesRepository lr;
    @Autowired
    private PostRepository pr;
    @Autowired
    private UsuarioRepository ur;

    public ResponseEntity<?> like(Integer postID, Integer usuarioID){
        try {
            Likes l = new Likes();
            pr.findById(postID).ifPresent(post -> l.setPost(post));
            ur.findById(usuarioID).ifPresent(user -> l.setUsuario(user));

            if(lr.likesUserPost(postID, usuarioID) > 0) {
                lr.delete(lr.getPostLiked(postID, usuarioID));
            } else {
                if (l.getPost() != null && l.getUsuario() != null)
                    lr.save(l);
                else
                   throw new Exception();
            }
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public Integer getCountLikesByPostId(Integer postId) {
        try {
            return lr.countLikesByPostId(postId);
        } catch (Exception e) {
            return -1;
        }
    }

    public List<Integer> getPostLikedByUser(Integer userId) {
        try {
            return lr.getPostLikedByUser(userId);
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
