package backend.api.services;

import backend.api.models.Imagen;

import backend.api.models.Usuario;
import backend.api.repositories.ImagenRepository;
import backend.api.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Date;
import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@Service
public class StorageService {

    @Autowired
    private ImagenRepository imagenRepository;
    @Autowired
    private UsuarioRepository uRepository;

    private final String FOLDER_PATH = System.getProperty("user.dir") + "/src/main/resources/static/images/";

    public ResponseEntity<?> uploadProfileImg(MultipartFile file, int userID) throws IOException {
        Usuario u = uRepository.findById(userID).orElse(null);

        if (u == null)
            return ResponseEntity.status(BAD_REQUEST).body("Usuario inexistente.");

        String imgName = "perfil_" + u.getUsername() + ".jpg";
        String filePath = FOLDER_PATH + "/perfiles/" + imgName;

        try {
            Imagen imagen = imagenRepository.findById(u.getFoto().getId()).orElse(null);
            if (imagen == null) {
                imagenRepository.save(Imagen.builder()
                        .usuario(u)
                        .name(imgName)
                        .type(file.getContentType())
                        .filePath(filePath).build());
            } else {
                imagen.setName(imgName);
                imagen.setFilePath(filePath);
                imagen.setType(file.getContentType());
            }

            u.setFoto(imagen);
            uRepository.save(u);

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
}
