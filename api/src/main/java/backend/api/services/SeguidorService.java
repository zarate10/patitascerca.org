package backend.api.services;

import backend.api.DTO.UsuarioDTO;
import backend.api.models.Seguidor;
import backend.api.models.Usuario;
import backend.api.repositories.SeguidorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Service
public class SeguidorService {
    @Autowired
    private SeguidorRepository sr;

    private List<UsuarioDTO> usersToDTO(List<Usuario> usuarios) {
        List<UsuarioDTO> usersDTO = new ArrayList<>();
        for (Usuario u : usuarios) {
            usersDTO.add(u.toDTO());
        }
        return usersDTO;
    }

    public ResponseEntity seguir(Map<String, Usuario> nuevaRelacion) {
        int seguidorID = nuevaRelacion.get("seguidor").getId();
        int seguidoID = nuevaRelacion.get("seguido").getId();
        try {
            Seguidor s = new Seguidor();
            s.setSeguido(nuevaRelacion.get("seguido"));
            s.setSeguidor(nuevaRelacion.get("seguidor"));

            if (sr.existeRelacion(seguidorID, seguidoID) > 0) {
                sr.delete(sr.getRelacion(seguidorID, seguidoID));
            } else {
                sr.save(s);
            }
            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<UsuarioDTO> obtenerSeguidoresDeUsuario(Integer id) {
        return usersToDTO(sr.findSeguidores(id));
    }

    public List<UsuarioDTO> obtenerSeguidosDeUsuario(Integer id) {
        return usersToDTO(sr.findSeguidos(id));
    }
}


