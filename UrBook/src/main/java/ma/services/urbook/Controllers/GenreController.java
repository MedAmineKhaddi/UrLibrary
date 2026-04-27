package ma.services.urbook.Controllers;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.GenreException;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.Payload.Response.ApiResponse;
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

    @PutMapping("/{genreId}")
    public ResponseEntity<?> updateGenre(@PathVariable(name = "genreId") Long genreId,@RequestBody GenreDTO genreDTO ) throws GenreException {
        return ResponseEntity.ok(genreService.updateGenre(genreId,genreDTO));
    }

    @DeleteMapping("genre/{id}")
    public ResponseEntity<?> deleteGenre(@PathVariable Long id) throws GenreException {
        genreService.deleteGenre(id);
        ApiResponse response = new ApiResponse("genre deleted seccessfuly, soft delete",true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("genre/hard/{id}")
    public ResponseEntity<?> handlDeleteGenre(@PathVariable Long id) throws GenreException {
        genreService.hardDeleteGenre(id);
        ApiResponse response = new ApiResponse("genre deleted seccessfuly, hard delete",true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/top-level")
    public ResponseEntity<?> getTopLevelGenres()
    {
        return ResponseEntity.ok(genreService.getTopLevelGenres());
    }

    @GetMapping("/total-active")
    public ResponseEntity<?> getTotalActiveGenres()
    {
        return ResponseEntity.ok(genreService.getTotalActiveGenres());
    }

    @GetMapping("/{genreId}/book-count")
    public ResponseEntity<?> getBookCountByGenre(@PathVariable Long genreId) {
        return ResponseEntity.ok(genreService.getBookCountByGenres(genreId));
    }

}
