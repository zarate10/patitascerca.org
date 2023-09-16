package patascerca.proyecto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import patascerca.proyecto.DTO.UsuarioDTO;
import patascerca.proyecto.models.Usuario;
import patascerca.proyecto.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("user")
public class UsuarioController {

    @Autowired // Dependency Injection - autowired le pasa una instancia de la clase UsuarioService a UsuarioController, designada en uservice.
    // esto con el objeto de no andar instanciando clases dentro de otras clases. Esto para ganar escalabilidad y no depender de una única instancia.
    private UsuarioService uservice;

    @GetMapping("/all")
    public List<UsuarioDTO> getAll() {
        return uservice.getAll();
    }

    @PostMapping("/register")
    public ResponseEntity create(@RequestBody Usuario usuario) {
        return uservice.create(usuario);
    }
}
