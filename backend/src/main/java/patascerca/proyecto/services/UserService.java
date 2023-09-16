package patascerca.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patascerca.proyecto.DTO.UsuarioDTO;
import patascerca.proyecto.models.User;
import patascerca.proyecto.repositories.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity create(User usuario) {
        try {
            usuario.setRegistrationDate(new Date());
            userRepository.save(usuario);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<UsuarioDTO> getAll() {
        List<UsuarioDTO> usersDTO = new ArrayList<>();

        for(User u : userRepository.findAll()) {
            usersDTO.add(u.toDTO());
        }

        return usersDTO;
    }

}
