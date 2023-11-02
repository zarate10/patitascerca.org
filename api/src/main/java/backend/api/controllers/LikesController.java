package backend.api.controllers;

import backend.api.models.Likes;
import backend.api.models.Usuario;
import backend.api.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("likes")
@CrossOrigin(origins = "http://localhost:4200")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping("like")
    public ResponseEntity<?> like(@RequestBody Map<String, Integer> body) {
        return likesService.like(body.get("postID"), body.get("usuarioID"));
    }

    @GetMapping("posts/{userID}")
    public List<Integer> postsLikedByUser(@PathVariable Integer userID) {
        return likesService.getPostLikedByUser(userID);
    }
}
