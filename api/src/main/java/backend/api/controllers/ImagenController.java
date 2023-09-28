package backend.api.controllers;

import backend.api.models.Imagen;
import backend.api.services.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("images")
public class ImagenController {

    @Autowired
    private StorageService service;

    @PostMapping("upload/profile/{userID}")
    public ResponseEntity<?> uploadProfileImg(@RequestParam("image") MultipartFile file, @PathVariable int userID) throws IOException {
        return service.uploadProfileImg(file, userID);
    }

    @PostMapping("upload/post/{postID}")
    public ResponseEntity<?> uploadImgToPost(@RequestParam("image") MultipartFile file, @PathVariable int postID) throws IOException {
        return service.uploadImgToPost(postID, file);
    }

    @GetMapping("{username}")
    public ResponseEntity<?> getImgProfileByUsername(@PathVariable String username) throws IOException {
        return service.getImgProfileByUsername(username);
    }

    @GetMapping("post/{postID}")
    public Map<String, String> getImgProfileByUsername(@PathVariable int postID) throws IOException {
        return service.getPostImgs(postID);
    }

    @GetMapping("post/img/{imgName}")
    public ResponseEntity<?> getPostImgByName(@PathVariable String imgName) throws IOException {
        return service.getPostImgByName(imgName);
    }
}
