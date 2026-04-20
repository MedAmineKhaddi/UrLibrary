package ma.services.urbook.services.impl;

import ma.services.urbook.Models.Genra;
import ma.services.urbook.Repositories.GenreRepository;
import ma.services.urbook.services.GenereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


public class GenereServiceImpl implements GenereService {

   final private GenreRepository genreRepository;

    public GenereServiceImpl(GenreRepository genreRepository){
        this.genreRepository = genreRepository;
    }

    @Override
    public Genra createGenre(Genra Genra) {
        return this.genreRepository.save(Genra);
    }
}
