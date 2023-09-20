package backend.api.services;

import backend.api.models.Comentario;
import backend.api.models.Post;
import backend.api.models.Usuario;
import backend.api.repositories.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;

@Service
public class ComentarioService {
    @Autowired
    private ComentarioRepository cr;

    public ResponseEntity comentar(Post postID, Usuario userID, String comment) {
        try {
            Comentario c = new Comentario();
            c.setComentario(comment);
            c.setUsuario(userID);
            c.setPost(postID);
            cr.save(c);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public ResponseEntity delete(Integer comentarioID) {
        try {
            cr.findById(comentarioID).ifPresent(comentario -> cr.delete(comentario));

            return ResponseEntity.status(OK).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }
}
