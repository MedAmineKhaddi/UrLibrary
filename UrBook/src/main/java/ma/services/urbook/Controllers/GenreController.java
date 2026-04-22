package ma.services.urbook.Controllers;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.GenreException;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.services.GenreService;
import org.aspectj.apache.bcel.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.createGenre(genreDTO));
    }

    @GetMapping()
    public ResponseEntity<?> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }

    @GetMapping("genre/{id}")
    public ResponseEntity<?> getGenreById(@PathVariable Long id) throws GenreException {
        return ResponseEntity.ok(genreService.getGenreById(id));
    }
}
