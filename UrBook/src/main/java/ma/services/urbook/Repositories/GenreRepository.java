package ma.services.urbook.Repositories;

import ma.services.urbook.Models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


public interface GenreRepository extends JpaRepository<Genre,Long> {

        /*
        * findByActiveTrueOrderByDisplayOrderAsc()
        findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc()
        findParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(Long ParentGenreId);
        long countByActiveTrue();
        @Query("select count(b) from book b where b.genre.id=:genreId")
        long countBookByGenre(@Param("genreId") Long genreId);
        *
        */

}
