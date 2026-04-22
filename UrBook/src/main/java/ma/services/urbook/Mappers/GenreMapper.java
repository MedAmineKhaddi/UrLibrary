package ma.services.urbook.Mappers;

import lombok.RequiredArgsConstructor;
import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;
import ma.services.urbook.Repositories.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class GenreMapper {

    @Autowired
    public GenreRepository genreRepository;

    public GenreDTO toDTO (Genre genre) {
        if (genre == null) {
            return null;
        }
        GenreDTO genreDTO = GenreDTO.builder()
                .id(genre.getId())
                .code(genre.getCode())
                .name(genre.getName())
                .description(genre.getDescription())
                .updatedAt(genre.getUpdatedAt())
                .createdAt(genre.getCreatedAt())
                .active(genre.getActive())
                .displayOrder(genre.getDisplayOrder())
                .build();

        if(genre.getParentGenre() != null) {
            genreDTO.setParentGenreId(genre.getParentGenre().getId());
            genreDTO.setParentGenreName(genre.getParentGenre().getName());
        }


        if(genre.getSubGenre() != null && !genre.getSubGenre().isEmpty()) {
            genreDTO.setSubGenre(genre.getSubGenre()
                    .stream()
                    .filter(subGenre-> subGenre.getActive())
                    .map(subGenre -> toDTO(subGenre))
                    .collect(Collectors.toList()));
        }


        return genreDTO;
    }

    public  Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }
        Genre genre =  Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .active(true)
                .displayOrder(genreDTO.getDisplayOrder())
                .build();

        if(genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId()).get();
            genre.setParentGenre(parentGenre);
        }

        return genre;

    }



}
