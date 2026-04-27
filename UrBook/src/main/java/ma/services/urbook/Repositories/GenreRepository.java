package ma.services.urbook.Repositories;

import ma.services.urbook.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


public interface GenreRepository extends JpaRepository<Genre,Long> {

        public List<Genre> findByActiveTrueOrderByDisplayOrderAsc();
        public List<Genre>findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
        public long countByActiveTrue();
        /*
        findParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(Long ParentGenreId);
        @Query("select count(b) from book b where b.genre.id=:genreId")
        long countBookByGenre(@Param("genreId") Long genreId);
        *
        */

}
