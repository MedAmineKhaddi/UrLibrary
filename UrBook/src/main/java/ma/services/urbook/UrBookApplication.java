package ma.services.urbook;

import ma.services.urbook.Models.Genre;
import ma.services.urbook.Repositories.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import java.util.List;

@SpringBootApplication
public class UrBookApplication {

    public static void main(String[] args) {
        SpringApplication.run(UrBookApplication.class, args);
    }


}
