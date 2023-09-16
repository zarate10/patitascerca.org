package backend.api.controllers;

import backend.api.models.Category;
import backend.api.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("create")
    public ResponseEntity create(@RequestBody Category c) {
        return categoryService.create(c);
    }

    // TO-DO: crear endpoint para eliminar categoria y para obtener las categorias con sus id.
}
