package backend.api.controllers;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Usuario;
import backend.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UsuarioController {

    @Autowired // Dependency Injection - autowired le pasa una instancia de la clase UsuarioService a UsuarioController, designada en uservice.
    private UsuarioService uservice;

    @GetMapping("/all")
    public List<UsuarioDTO> getAll() {
        return uservice.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody Usuario user) {
        return uservice.create(user);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody String username, @RequestBody String password){
        return uservice.login(username,password);
    }
}
