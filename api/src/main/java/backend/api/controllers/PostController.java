package backend.api.controllers;

import backend.api.models.Post;
import backend.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("post")
@CrossOrigin(origins = "http://localhost:4200")
public class PostController {
    // devolver usuario DTO
    @Autowired
    private PostService postService;

    @GetMapping("all")
    public List<Post> get() {
        return postService.getAll();
    }

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Post p) {
        return postService.create(p);
    }

    @DeleteMapping("delete")
    public ResponseEntity create(@RequestBody Map<String, Integer> postID) {
        return postService.delete(postID.get("post_id"));
    }

    @GetMapping("{id}")
    public Post getPostById(@PathVariable Integer id) {
        return postService.getPostById(id);
    }
}
