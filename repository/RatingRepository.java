package encoway.springframework.videoapplication.repository;

import encoway.springframework.videoapplication.model.Rating;
import org.springframework.data.repository.CrudRepository;

/**
 * represents the Rating persistence, which will act as database repository for the ratings
 * @autor Mohamad Alkasem
 * @version 1.0
 */
public interface RatingRepository extends CrudRepository<Rating, Long> {
}
