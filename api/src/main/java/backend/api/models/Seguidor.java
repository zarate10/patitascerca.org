package backend.api.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Seguidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne()
    @JoinColumn(name = "seguidor_id", referencedColumnName = "id")
    private Usuario seguidor;
    @ManyToOne
    @JoinColumn(name = "seguido_id", referencedColumnName = "id")
    private Usuario seguido;
}
