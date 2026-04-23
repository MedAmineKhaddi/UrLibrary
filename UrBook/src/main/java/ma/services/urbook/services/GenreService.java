package ma.services.urbook.services;

import ma.services.urbook.Exceptions.GenreException;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;
import org.springframework.data.domain.Page;

import java.awt.print.Pageable;
import java.util.List;


public interface GenreService {
    GenreDTO createGenre(GenreDTO Genre);
    List<GenreDTO> getAllGenres();
    GenreDTO getGenreById(Long id) throws GenreException;

    GenreDTO updateGenre(Long genreId, GenreDTO GenreDTO) throws GenreException;
    void deleteGenre(Long genreId) throws GenreException;
    void hardDeleteGenre(Long genreId) throws GenreException;

    //List<GenreDTO> getAllActiveGenresWithSubGenres();
    //List<GenreDTO> getTopLevelGenres();
    //Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);
    //Long getTotalActiveGenres();
    //Long getBookCountByGenres(Long genreId);

}
