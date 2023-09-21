package backend.api.controllers;

import backend.api.DTO.CategoryDTO;
import backend.api.models.Category;
import backend.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("category")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Category c) {
        return categoryService.create(c);
    }

    @DeleteMapping("delete/{category_id}/{user_id}")
    public ResponseEntity delete(@PathVariable int category_id, @PathVariable int user_id) {
        return categoryService.delete(category_id, user_id);
    }

    @GetMapping("all")
    public List<CategoryDTO> getAll() {
        return categoryService.getAll();
    }
}
