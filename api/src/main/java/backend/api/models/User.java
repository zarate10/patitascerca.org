package backend.api.models;

import backend.api.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@AllArgsConstructor // genera un constructor con 1 parámetro para cada atributo de la clase
@NoArgsConstructor // genera un constructor sin parámetros
@Data // seteamos de forma implícita setters, getters, toString, etc (los famosos POJO) a la clase.
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // GeneratedValue hace que el ID sea auto-incremental.
    private int id;
    private String username;
    private String password;
    private String email;
    private int phone;
    private String city;
    private String descripcion;
    private Date registrationDate;
    private String photo;
    private int rank;

    public UserDTO toDTO() {
        UserDTO dto = new UserDTO();
        dto.username = this.username;
        dto.descripcion = this.descripcion;
        dto.email = this.email;
        dto.registrationDate = this.registrationDate.toString();
        dto.photo = this.photo;
        dto.rank = this.rank;
        return dto;
    }
}
