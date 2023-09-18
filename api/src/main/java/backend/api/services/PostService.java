package backend.api.services;

import backend.api.models.Post;
import backend.api.models.Usuario;
import backend.api.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;

import static org.springframework.http.HttpStatus.*;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;

    public ResponseEntity create(Post p) {
        try {
            p.setDate(new Date());
            postRepository.save(p);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(CREATED).build();
    }

    public ResponseEntity update(Post post){
        try{
            Post newPost = postRepository.findById(post.getId()).get();
            newPost.setCategory(post.getCategory());
            postRepository.save(newPost);
            return ResponseEntity.status(OK).build();
        }catch (Exception e){
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

}
