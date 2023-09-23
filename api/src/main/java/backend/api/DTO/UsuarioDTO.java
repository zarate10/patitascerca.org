package backend.api.DTO;

import backend.api.models.Imagen;

public class UsuarioDTO {
    public int id;
    public String username;
    public String email;
    public String descripcion;
    public String fechaRegistro;
    public Imagen foto;
    public int rango;
}
