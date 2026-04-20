package ma.services.urbook.services;

import ma.services.urbook.Models.Genre;
import ma.services.urbook.Payload.DTO.GenreDTO;


public interface GenreService {
    GenreDTO createGenre(GenreDTO Genre);
}
