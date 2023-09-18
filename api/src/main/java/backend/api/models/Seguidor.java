package backend.api.models;

import backend.api.DTO.SeguidorDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Seguidor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "seguidor_id")
    private Usuario seguidor;

    @ManyToOne
    @JoinColumn(name = "usuario_seguido_id")
    private Usuario siguiendo;

    public SeguidorDTO toDTO(){
        SeguidorDTO dto = new SeguidorDTO();
        dto.id = this.id;
        dto.siguiendo = this.siguiendo;
        dto.seguidor = this.seguidor;
        return dto;
    }
}
