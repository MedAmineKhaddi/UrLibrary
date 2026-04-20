package ma.services.urbook.Payload.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;


@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class GenreDTO {

    private Long id;

    @NotBlank(message = "Genre Code is Mandatory")
    private String code;

    @NotBlank(message = "Genre Name is Mandatory")
    private String name;

    @Size(max=500, message = "display order can not be negative")
    private String description;

    @Min(value = 0, message = "display order can not be negative")
    private Integer displayOrder=0;

    private boolean active;

    private Long parentGenreId;

    private String parentGenreName;

    private List<GenreDTO> subGenre;

    private Long bookCount;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}