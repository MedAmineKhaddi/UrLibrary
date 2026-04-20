package ma.services.urbook.Controllers;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.services.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genreDTO) {
        return ResponseEntity.ok(genreService.createGenre(genreDTO));
    }

}
