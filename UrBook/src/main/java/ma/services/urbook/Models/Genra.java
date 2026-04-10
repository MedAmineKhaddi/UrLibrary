package ma.services.urbook.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Genra {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le code du livre est obligatoire")
    private String code;
    @NotBlank(message = "Le nom du livre est obligatoire")
    private String name;
    @Size(max = 500, message = "La description du livre est obligatoire")
    private String description;
    @Min(value = 0, message = "Le displayOrder du livre est obligatoire être supérieur à 0")
    private int displayOrder;
    @Column(nullable = false)
    private boolean active;

    @ManyToOne
    private Genra parentGenre;

    @OneToMany
    private List<Genra> subGenres;

    private LocalDateTime creationDate;


}
