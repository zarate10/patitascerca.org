package backend.api.services;

import backend.api.DTO.CategoryDTO;
import backend.api.models.Category;
import backend.api.models.Usuario;
import backend.api.repositories.CategoryRepository;
import backend.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    public ResponseEntity<?> create(Category c) {
        try {
            categoryRepository.save(c);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(CREATED).build();
    }

    public ResponseEntity<?> delete(int id, int user_id) {
        try {
            Usuario usuario = usuarioRepository.findById(user_id).orElse(null);

            if (usuario != null && usuario.getRango() > 2) {
                categoryRepository.deleteById(id);
                return ResponseEntity.status(OK).build();
            } else {
                return ResponseEntity.status(FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<CategoryDTO> getAll() {
        List<CategoryDTO> categorias = new ArrayList<>();

        for(Category c : categoryRepository.findAll()) {
            categorias.add(c.toDTO());
        }

        return categorias;
    }
}
