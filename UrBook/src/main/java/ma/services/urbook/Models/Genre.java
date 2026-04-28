package ma.services.urbook.Models;

import jakarta.persistence.*;
import jakarta.persistence.CascadeType;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.*;

import java.time.LocalDateTime;
import java.util.*;

@Entity @AllArgsConstructor @NoArgsConstructor @Data @Builder
public class Genre {

    @Id @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genre Name is Mandatory")
    private String name;

    @Size(max=500, message = "display order can not be negative")
    private String description;

    @Min(value = 0, message = "display order can not be negative")
    private Integer displayOrder=0;

    @Column(nullable = false)
    private Boolean active=true;

    @ManyToOne
    private Genre parentGenre;

    @OneToMany(mappedBy = "parentGenre", cascade = CascadeType.REMOVE)
    private List<Genre> subGenre=new ArrayList<Genre>();

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
