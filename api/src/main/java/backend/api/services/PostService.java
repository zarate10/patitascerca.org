package backend.api.services;

import backend.api.models.Post;
import backend.api.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class PostService {
    @Autowired
    private PostRepository pr;

    public List<Post> getAll() {
        return pr.findAll();
    }

    public ResponseEntity create(Post p) {
        try {
            p.setFecha(new Date());
            pr.save(p);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(CREATED).build();
    }

    public ResponseEntity delete(int postID) {
        try {
            pr.findById(postID).ifPresent(post -> pr.delete(post));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(CREATED).build();
    }

    public Post getPostById(Integer id) {
        return pr.findById(id).orElse(null);
    }
}
