package ma.services.urbook.Payload.DTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
public class BookDTO {

    private Long id;

    @NotBlank(message = "ISBN is mandatory")
    private String isbn;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Author is mandatory")
    private String author;

    private Long genreId;

    private String genreName;

    private String genreCode;

    private String publisher;

    private LocalDate publishedDate;

    private String language;

    @Min(value = 1, message = "Pages must be at least 1")
    private Integer pages;

    private String description;

    private Integer totalCopies;

    private BigDecimal price;

    private String coverImageUrl;

    private Boolean active=true;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer availableCopies;

    private Boolean alreadyHaveLoan;

    private Boolean alreadyHaveReservation;

}
