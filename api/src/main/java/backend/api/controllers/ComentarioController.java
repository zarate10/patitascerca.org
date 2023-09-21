package backend.api.controllers;

import backend.api.models.Comentario;
import backend.api.services.ComentarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("comments")
public class ComentarioController {
    @Autowired
    private ComentarioService cs;

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Comentario c) {
        return cs.comentar(c);
    }

    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestBody Map<String, Integer> data) {
        return cs.delete(data.get("comentarioID"));
    }
}
