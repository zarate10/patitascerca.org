package backend.api.services;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import backend.api.repositories.PostRepository;
import backend.api.repositories.SeguidorRepository;
import backend.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.OK;

@Service
public class SeguidorService {
    @Autowired
    private SeguidorRepository seguidorRepository;
    private UsuarioRepository usuarioRepository;

    public List<UsuarioDTO> obtenerSeguidoresDeUsuario(Integer id) {
        List<UsuarioDTO> usersDTO = new ArrayList<>();

        for (Usuario u : seguidorRepository.findSeguidores(id)) {
            usersDTO.add(u.toDTO());
        }
        return usersDTO;
    }


    public List<UsuarioDTO> obtenerSeguidosDeUsuario(Integer id) {
        List<UsuarioDTO> usersDTO = new ArrayList<>();

        for (Usuario u : seguidorRepository.findSeguidos(id)) {
            usersDTO.add(u.toDTO());
        }
        return usersDTO;
    }


    public ResponseEntity seguir(Map<String, Usuario> nuevaRelacion) {
        Seguidor s = new Seguidor();
        s.setSeguido(nuevaRelacion.get("seguido"));
        s.setSeguidor(nuevaRelacion.get("seguidor"));
        seguidorRepository.save(s);

        return ResponseEntity.status(OK).build();
    }
}

    /*public void dejarDeSeguirUsuario(Usuario seguidor, Usuario usuarioSeguido) {
        Seguidor relacion = seguidorRepository.findRelacion(seguidor.getId(),usuarioSeguido.getId());
        seguidorRepository.delete(relacion);

    }
     */


