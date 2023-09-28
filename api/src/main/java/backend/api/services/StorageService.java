package backend.api.services;

import backend.api.models.Imagen;

import backend.api.models.Post;
import backend.api.models.Usuario;
import backend.api.repositories.ImagenRepository;
import backend.api.repositories.PostRepository;
import backend.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

import static org.springframework.http.HttpStatus.*;

@Service
public class StorageService {

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private UsuarioRepository uRepository;
    @Autowired
    private PostRepository postRepository;

    private final String FOLDER_PATH = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    public ResponseEntity<?> uploadProfileImg(MultipartFile file, int userID) throws IOException {
        Usuario u = uRepository.findById(userID).orElse(null);

        if (u == null)
            return ResponseEntity.status(BAD_REQUEST).body("Usuario inexistente.");

        String imgName = "perfil_" + u.getUsername() + ".jpg";
        String filePath = FOLDER_PATH + "perfiles/" + imgName;

        try {
            Imagen img;

            if (u.getFoto() == null) {
                img = Imagen.builder()
                        .usuario(u)
                        .name(imgName)
                        .type(file.getContentType())
                        .fechaCreacion(new Date())
                        .filePath(filePath).build();

                imagenRepository.save(img);
                u.setFoto(img);
                uRepository.save(u);
            } else {
                img = u.getFoto();
                img.setName(imgName);
                img.setFilePath(filePath);
                img.setType(file.getContentType());
                imagenRepository.save(img);
            }
            file.transferTo(new File(filePath));
            return ResponseEntity.status(OK).body("Imagen cargada correctamente: " + file.getOriginalFilename() + "_" + new Date());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error al cargar la imagen");
        }
    }

    public ResponseEntity<?> getImgProfileByUsername(String username) throws IOException {
        Usuario u = uRepository.findById(uRepository.findIdByUsername(username)).orElse(null);

        if (u == null)
            return ResponseEntity.status(BAD_REQUEST).body("Usuario inexistente");

        Optional<Imagen> imagen = imagenRepository.findById(u.getFoto().getId());
        String filePath = imagen.get().getFilePath();

        byte[] img = Files.readAllBytes(new File(filePath).toPath());

        return ResponseEntity.status(OK).contentType(MediaType.valueOf("image/png")).body(img);
    }

    public ResponseEntity<?> uploadImgToPost(int postID, MultipartFile file) {
        Post p = postRepository.findById(postID).orElse(null);

        if (p == null)
            return ResponseEntity.status(BAD_REQUEST).body("Post inexistente.");

        String imgName = "post_"
                + p.getId()
                + "-"
                + imagenRepository.countImgPost(p.getId()) + ".jpg";

        String filePath = FOLDER_PATH + "posts/" + imgName;

        Imagen img = Imagen.builder()
                .post(p)
                .name(imgName)
                .type(file.getContentType())
                .fechaCreacion(new Date())
                .filePath(filePath).build();

        imagenRepository.save(img);
        try {
            file.transferTo(new File(filePath));
            return ResponseEntity.status(OK).body("Imagen cargada correctamente: " + file.getOriginalFilename() + "_" + new Date());
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body("Error al cargar la imagen");
        }
    }

    public ResponseEntity<?> getPostImgByName(String name) throws IOException {
        Imagen imagen = imagenRepository.getPostImgByName(name);
        String filePath = imagen.getFilePath();

        byte[] img = Files.readAllBytes(new File(filePath).toPath());

        return ResponseEntity.status(OK).contentType(MediaType.valueOf("image/png")).body(img);
    }

    public Map<String, String> getPostImgs(int postID) throws IOException {
        Map<String, String> urlsImg = new HashMap<>();
        List<Imagen> imagenes =  imagenRepository.getAllPostImgByName("post_" + postID);

        for(int i = 0; i < imagenes.size(); i++) {
            urlsImg.put(
                    "img_" + i,
                    "http://127.0.0.1:8080/images/post/img/" + imagenes.get(i).getName());
        }
        return urlsImg;
    }

}
