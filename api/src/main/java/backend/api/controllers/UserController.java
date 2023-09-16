package backend.api.controllers;

import backend.api.DTO.UserDTO;
import backend.api.models.User;
import backend.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired // Dependency Injection - autowired le pasa una instancia de la clase UsuarioService a UsuarioController, designada en uservice.
    private UserService uservice;

    @GetMapping("/all")
    public List<UserDTO> getAll() {
        return uservice.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody User user) {
        return uservice.create(user);
    }
}
