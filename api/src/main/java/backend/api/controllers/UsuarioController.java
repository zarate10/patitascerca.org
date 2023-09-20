package backend.api.controllers;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Usuario;
import backend.api.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "http://localhost:4200")
public class UsuarioController {
    @Autowired // Dependency Injection - autowired le pasa una instancia de la clase UsuarioService a UsuarioController, designada en uservice.
    private UsuarioService uservice;

    @GetMapping("all")
    public List<UsuarioDTO> getAll() {
        return uservice.getAll();
    }

    @PostMapping("register")
    public ResponseEntity create(@RequestBody Usuario user) {
        return uservice.create(user);
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody Map<String, String> data){
        return uservice.login(data);
    }

    @PutMapping("updateProfile")
    public ResponseEntity updateProfile(@RequestBody Usuario user){return uservice.updateProfile(user);}

    @PatchMapping("updatePassword")
    public ResponseEntity updatePassword(@RequestBody Map<String,String> data){return uservice.updatePassword(data);}

    @DeleteMapping("delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id, @RequestBody Usuario u) {
        return uservice.deleteUser(id, u);
    }
}
