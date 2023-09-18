package backend.api.services;

import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import backend.api.repositories.PostRepository;
import backend.api.repositories.SeguidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeguidorService {
    @Autowired
    private SeguidorRepository seguidorRepository;

    public List<Usuario> obtenerSeguidoresDeUsuario(Usuario usuario) {
        return seguidorRepository.findSeguidores(usuario.getId());
    }

    public List<Seguidor> obtenerSeguidosDeUsuario(Usuario usuario) {
        return seguidorRepository.findSeguidos(usuario.getId());
    }

    public void seguirUsuario(Usuario seguidor, Usuario usuarioSeguido) {
        Seguidor nuevaRelacion = new Seguidor();
        nuevaRelacion.setSeguidor(seguidor);
        nuevaRelacion.setSiguiendo(usuarioSeguido);
        seguidorRepository.save(nuevaRelacion);
    }

    public void dejarDeSeguirUsuario(Usuario seguidor, Usuario usuarioSeguido) {
        Seguidor relacion = seguidorRepository.findRelacion(seguidor.getId(),usuarioSeguido.getId());
        seguidorRepository.delete(relacion);
    }
}

