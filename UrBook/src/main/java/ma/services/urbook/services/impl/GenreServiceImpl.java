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

    @Override
    public GenreDTO getGenreById(Long id) throws GenreException {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> new GenreException("Genre not found"));
        GenreDTO dto = genreMapper.toDTO(genre);
        return dto;
    }

    @Override
    public GenreDTO updateGenre(Long genreId, GenreDTO GenreDTO) throws  GenreException {
            Genre existingGenre =  genreRepository.findById(genreId).orElseThrow(() -> new GenreException("Genre not found"));
            genreMapper.updateEntityFromDTO(GenreDTO,existingGenre);
            Genre updatedGenre = genreRepository.save(existingGenre);
            return genreMapper.toDTO(updatedGenre);
    }

    @Override
    public void deleteGenre(Long genreId) throws GenreException {
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(() -> new GenreException("Genre not found"));
        existingGenre.setActive(false);
        genreRepository.delete(existingGenre);
    }

    @Override
    public void hardDeleteGenre(Long genreId) throws GenreException{
        Genre existingGenre = genreRepository.findById(genreId).orElseThrow(() -> new GenreException("Genre not found"));
        genreRepository.delete(existingGenre);
    }



    @Override
    public List<GenreDTO> getTopLevelGenres() {
        List<Genre> topLevelGenres = genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public Long getTotalActiveGenres() {
        return genreRepository.countByActiveTrue();
    }

    @Override
    public List<GenreDTO> getAllActiveGenresWithSubGenres() {
        List<Genre> topLevelGenres = genreRepository
                .findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
        return genreMapper.toDTOList(topLevelGenres);
    }

    @Override
    public Long getBookCountByGenres(Long genreId) {
        return 0L;
    }
}
