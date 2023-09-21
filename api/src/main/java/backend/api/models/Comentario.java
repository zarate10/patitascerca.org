package backend.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String comentario;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    Post post;
}
