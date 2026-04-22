package ma.services.urbook.services.impl;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Exceptions.GenreException;
import ma.services.urbook.Mappers.GenreMapper;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Repositories.GenreRepository;
import ma.services.urbook.services.GenreService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

   final private GenreRepository genreRepository;
   final private GenreMapper genreMapper;


    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
       Genre genre = genreMapper.toEntity(genreDTO);
       Genre savedGenre = genreRepository.save(genre);
        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream().map(genreMapper::toDTO).collect(Collectors.toList());
    }

    //add it
    @Override
    public GenreDTO getGenreById(Long id) throws GenreException {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreException("Genre not found"));
        GenreDTO dto = genreMapper.toDTO(genre);
        return dto;
    }


    @Override
    public void hardDeleteGenre(Long genreId) {

    }

    @Override
    public void deleteGenre(Long genreId) {

    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO GenreDTO) {
        return null;
    }
}
