package ma.services.urbook.services.impl;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Mappers.GenreMapper;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Repositories.GenreRepository;
import ma.services.urbook.services.GenreService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
}
