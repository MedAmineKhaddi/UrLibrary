package ma.services.urbook.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data @NoArgsConstructor
@AllArgsConstructor @Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @ManyToOne
    @JoinColumn(name = "genre_id")
    private Genre genre;

    private String publisher;

    private LocalDate publishedDate;

    private String language;

    private Integer pages;

    private String description;

    @Column(nullable = false)
    private Integer totalCopies;

    private BigDecimal price;

    private String coverImageUrl;

    @Column(nullable = false)
    private Boolean active=true;

    @Column(nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(nullable = false)
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Column(nullable = false)
    private Integer availableCopies;

    @AssertTrue(message = "Available copies can't exceed total copies")
    public boolean isAvailableCopiesValid() {
        if(totalCopies ==null || availableCopies == null)
            return true;

        return availableCopies <=totalCopies;
    }



}
