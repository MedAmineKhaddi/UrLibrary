package ma.services.urbook.services.impl;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Mappers.GenreMapper;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Repositories.GenreRepository;
import ma.services.urbook.services.GenreService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

   final private GenreRepository genreRepository;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {
       Genre genre = Genre.builder()
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

       GenreDTO dto = GenreDTO.builder()
               .id(savedGenre.getId())
               .code(savedGenre.getCode())
               .name(savedGenre.getName())
               .description(savedGenre.getDescription())
               .displayOrder(savedGenre.getDisplayOrder())
               .active(savedGenre.getActive())
               .createdAt(genre.getCreatedAt())
               .updatedAt(genre.getUpdatedAt())
               .build();

       if(savedGenre.getParentGenre()!=null)
       {
           dto.setParentGenreId(savedGenre.getParentGenre().getId());
           dto.setParentGenreName(savedGenre.getParentGenre().getName());
       }

        return dto;
    }





}
