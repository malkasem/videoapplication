package encoway.springframework.videoapplication.repository;

import encoway.springframework.videoapplication.model.Genre;
import org.springframework.data.repository.CrudRepository;

/**
 * represents the Genre persistence, which will act as database repository for the genres
 * @autor Mohamad Alkasem
 * @version 1.0
 *
 */
public interface GenreRepository extends CrudRepository<Genre, Long> {
}
