package backend.api.controllers;

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

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Post p) {
        return postService.create(p);
    }

    @PatchMapping("update")
    public ResponseEntity update(@RequestBody Post p) {
        return postService.update(p);
    }

    @GetMapping("all")
    public List<Post> get() {
        return postService.getAll();
    }
}
