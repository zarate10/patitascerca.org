package backend.api.services;

import backend.api.models.Category;
import backend.api.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public ResponseEntity create(Category c) {
        try {
            categoryRepository.save(c);
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
        return ResponseEntity.status(CREATED).build();
    }


}
