package backend.api.controllers;

import backend.api.models.Usuario;
import backend.api.services.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("likes")
public class LikesController {
    @Autowired
    private LikesService likesService;

    @PostMapping("like/{idPost}")
    public ResponseEntity like(@PathVariable Integer idPost, @RequestBody Usuario usuario) { return likesService.like(idPost, usuario); }
}
