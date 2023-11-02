package backend.api.DTO;


import java.util.Date;

public class PostDTO {
    public int id;
    public CategoryDTO categoria;
    public UsuarioDTO usuario;
    public String descripcion;
    public Date fecha;
    public String ubicacion;
    public String imagen;
    public int totalLikes = 0;
    public int totalComentarios = 0;
}
