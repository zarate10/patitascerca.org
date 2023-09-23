package backend.api.controllers;

import backend.api.models.Usuario;
import backend.api.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("images")
public class ImagenController {

    @Autowired
    private StorageService service;

    @PostMapping("upload/profile/{userID}")
    public ResponseEntity<?> uploadProfileImg(@RequestParam("image") MultipartFile file, @PathVariable int userID) throws IOException {
        return service.uploadProfileImg(file, userID);
    }


    @GetMapping("{username}")
    public ResponseEntity<?> getImgProfileByUsername(@PathVariable String username) throws IOException {
        return service.getImgProfileByUsername(username);
    }
}
