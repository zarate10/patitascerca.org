package backend.api.controllers;

import backend.api.models.Post;
import backend.api.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Post p) {
        return postService.create(p);
    }

    @PostMapping("update")
    public ResponseEntity update(@RequestBody Post p) {
        return postService.update(p);
    }

}
