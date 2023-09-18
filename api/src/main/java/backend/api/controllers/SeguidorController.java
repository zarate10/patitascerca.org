package backend.api.controllers;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Usuario;
import backend.api.services.SeguidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("follows")
public class SeguidorController {
    @Autowired
    private SeguidorService seguidorService;

    @PostMapping("seguir")
    public ResponseEntity seguir(@RequestBody Map<String, Usuario> nuevaRelacion) {
        return seguidorService.seguir(nuevaRelacion);
    }

    @PostMapping("unfollow")
    public ResponseEntity unfollow(@RequestBody Map<String, Usuario> relacion) {
        return seguidorService.unfollow(relacion);
    }

    @GetMapping("seguidores/{id}")
    public List<UsuarioDTO> seguidores(@PathVariable Integer id) {
        return seguidorService.obtenerSeguidoresDeUsuario(id);
    }

    @GetMapping("seguidos/{id}")
    public List<UsuarioDTO> seguidos(@PathVariable Integer id) {
        return seguidorService.obtenerSeguidosDeUsuario(id);
    }
}
