package backend.api.services;

import backend.api.models.Likes;
import backend.api.models.Post;
import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import backend.api.repositories.LikesRepository;
import backend.api.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Service
public class LikesService {
    @Autowired
    private LikesRepository likesRepository;
    @Autowired
    private PostRepository postRepository;
    public ResponseEntity like(Integer post_id, Usuario usuario){
        try {
            Likes l = new Likes();
            l.setPost(postRepository.getById(post_id));
            l.setUsuario(usuario);
            likesRepository.save(l);
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
