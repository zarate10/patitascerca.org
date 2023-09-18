package backend.api.controllers;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Usuario;
import backend.api.repositories.SeguidorRepository;
import backend.api.services.SeguidorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("seguidor")
public class SeguidorController {
    @Autowired
    private SeguidorService seguidorService;

    @GetMapping("seguidores/{id}")
    public List<UsuarioDTO> seguidores(@PathVariable Integer id) {return seguidorService.obtenerSeguidoresDeUsuario(id);}
    @GetMapping("seguidos/{id}")
    public List<UsuarioDTO> seguidos(@PathVariable Integer id) {return seguidorService.obtenerSeguidosDeUsuario(id);}

    @PostMapping("seguir")
    public ResponseEntity seguir(@RequestBody Map<String, Usuario> nuevaRelacion) {
        return seguidorService.seguir(nuevaRelacion);
    }

    //@DeleteMapping("/dejarDeSeguir")
    //public void dejarDeSeguir(@RequestBody Usuario seguido, @RequestBody Usuario seguidor) {seguidorService.dejarDeSeguirUsuario(seguidor,seguido);}

}
