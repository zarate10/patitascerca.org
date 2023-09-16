package backend.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;
    private String description;
    private Date date;
    private String location;
    private String image;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private Usuario usuario;
}
