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


    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
       Genre genre = Genre.builder()
               .id(genreDTO.getId())
               .name(genreDTO.getName())
               .code(genreDTO.getCode())
               .description(genreDTO.getDescription())
               .displayOrder(genreDTO.getDisplayOrder())
               .active(true)
               .build();
       if(genreDTO.getParentGenreId()!=null)
       {
           Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId()).get();
           genre.setParentGenre(parentGenre);
       }
        Genre savedGenre = genreRepository.save(genre);

       GenreDTO dto = GenreMapper.toDTO(savedGenre);
        return dto;
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll().stream().map(GenreMapper::toDTO).collect(Collectors.toList());
    }





}
