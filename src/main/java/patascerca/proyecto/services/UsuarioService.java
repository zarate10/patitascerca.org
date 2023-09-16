package patascerca.proyecto.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import patascerca.proyecto.DTO.UsuarioDTO;
import patascerca.proyecto.models.Usuario;
import patascerca.proyecto.repositories.UsuarioRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public ResponseEntity create(Usuario usuario) {
        try {
            usuario.setFecha_registro(new Date());
            usuarioRepository.save(usuario);
            return ResponseEntity.status(CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).build();
        }
    }

    public List<UsuarioDTO> getAll() {
        List<UsuarioDTO> usuarioDTOs = new ArrayList<>();

        for(Usuario u : usuarioRepository.findAll()) {
            usuarioDTOs.add(u.toDTO());
        }

        return usuarioDTOs;
    }

}
