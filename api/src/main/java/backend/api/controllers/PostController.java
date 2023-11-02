package backend.api.controllers;

import backend.api.DTO.PostDTO;
import backend.api.models.Post;
import backend.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("post")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    @Autowired
    private PostService postService;

    @GetMapping("all")
    public List<PostDTO> get() {
        return postService.getAll();
    }

    @PostMapping("create")
    public ResponseEntity<?> create(@RequestBody Post p) {
        return postService.create(p);
    }

    @PostMapping("update")
    public ResponseEntity<?> update(@RequestBody Post p) {
        return postService.updatePost(p);
    }

    @DeleteMapping("delete/{postID}")
    public ResponseEntity<?> delet(@PathVariable Integer postID) {
        return postService.delete(postID);
    }

    @GetMapping("{id}")
    public PostDTO getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }
}
