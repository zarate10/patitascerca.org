package backend.api.controllers;

import backend.api.models.Usuario;
import backend.api.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("likes")
public class LikesController {
    @Autowired
    private LikesService likesService;


    @PostMapping("like")
    public ResponseEntity like(@RequestBody Map<String, Integer> body) {
        return likesService.like(body.get("postID"), body.get("usuarioID"));
    }
}
