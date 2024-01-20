package encoway.springframework.videoapplication.repository;

import encoway.springframework.videoapplication.model.Movie;
import org.springframework.data.repository.CrudRepository;

/**
 * represents the Movie persistence, which will act as database repository for the movies
 * @autor Mohamad Alkasem
 * @version 1.0
 */
public interface MovieRepository extends CrudRepository<Movie, Long> {
}
