package backend.api.models;

import backend.api.DTO.UsuarioDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor // genera un constructor con 1 parámetro para cada atributo de la clase
@NoArgsConstructor // genera un constructor sin parámetros
@Data // seteamos de forma implícita setters, getters, toString, etc (los famosos POJO) a la clase.
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue hace que el ID sea auto-incremental.
    private int id;
    private String username;
    private String password;
    private String email;
    private int telefono;
    private String ciudad;
    private String descripcion;
    private Date fechaRegistro;
    private String foto;
    private int rango;

    @OneToMany(mappedBy = "seguidor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguidor> seguidores = new ArrayList<>();
    @OneToMany(mappedBy = "seguido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seguidor> seguidos = new ArrayList<>();
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts = new ArrayList<>();
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> postsLiked = new ArrayList<>();

    public UsuarioDTO toDTO() {
        UsuarioDTO dto = new UsuarioDTO();
        dto.username = this.username;
        dto.descripcion = this.descripcion;
        dto.email = this.email;
        dto.fechaRegistro = this.fechaRegistro.toString();
        dto.foto = this.foto;
        dto.rango = this.rango;
        return dto;
    }
}
