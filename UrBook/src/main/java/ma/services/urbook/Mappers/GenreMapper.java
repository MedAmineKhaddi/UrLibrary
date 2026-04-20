package ma.services.urbook.Mappers;

import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;

import java.util.stream.Collectors;

public class GenreMapper {

    public static GenreDTO toDTO (Genre genre) {
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



}
