package ma.services.urbook.Repositories;

import ma.services.urbook.Models.Genra;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.support.Repositories;

public interface GenreRepository extends JpaRepository<Genra,Long> {
}
