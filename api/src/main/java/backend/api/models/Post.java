package backend.api.models;

import backend.api.DTO.PostDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", referencedColumnName = "id")
    private Category categoria;

    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;

    private String descripcion;
    private Date fecha;
    private String ubicacion;
    private String imagen;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Likes> likes;

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comentario> comentarios;

    public PostDTO toDTO() {
        PostDTO dto = new PostDTO();
        dto.id = this.id;
        dto.categoria = this.categoria.toDTO();
        dto.fecha = this.fecha;
        dto.imagen = this.imagen;
        dto.descripcion = this.descripcion;
        dto.ubicacion = this.ubicacion;
        dto.usuario = this.usuario.toDTO();
        return dto;
    }
}
