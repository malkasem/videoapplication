package encoway.springframework.videoapplication.service.implementation;

import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.repository.RatingRepository;
import encoway.springframework.videoapplication.service.interfaces.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the RatingService interface.
 * <p>
 * This class provides the concrete implementation of the RatingService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected and
 * instantiated by Spring's component scanning.
 * <p>
 * It uses  a RatingRepository to perform operations related to ratings.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.RatingService
 */

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private final RatingRepository ratingRepository;

    /**
     * constructs a new RatingServiceImpl object with the parameter
     * @param ratingRepository to be used for the rating`s operations
     */
    public RatingServiceImpl(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * @method find the rating in the database by its id
     * @param ratingId is the ID of the object to be found
     * @return the rating with the given id or null if it's not exists
     */
    @Override
    public Optional<Rating> findById(Long ratingId) {
        return ratingRepository.findById(ratingId);
    }

    /**
     * @method find set of ratings in the database
     * @return set of rating or null
     */
    @Override
    public Set<Rating> findAll() {
        Set<Rating> ratings = new HashSet<>();
        ratingRepository.findAll().forEach(rating -> ratings.add(rating));
        return ratings;
    }

    /**
     * @method saves rating to the database
     * @param rating of the entity Rating
     * @return the saved rating
     */
    @Override
    public Rating save(Rating rating) {
        return ratingRepository.save(rating);
    }

    /**
     * saves a set of ratings to teh database
     *
     * @param ratings set of entities to be found
     * @return set of the saved_ratings or null
     */
    @Override
    public Set<Rating> saveAll(Set<Rating> ratings) {

        Set<Rating> saved_ratings = new HashSet<>();
        ratingRepository.saveAll(ratings).forEach(rating -> saved_ratings.add(rating));
        return saved_ratings;
    }

    /**
     * delete a rating from the database
     *
     * @param rating which would be deleted from the database
     */
    @Override
    public void delete(Rating rating) {
        ratingRepository.delete(rating);
    }

    /**
     * delete a rating from the database by its id
     *
     * @param ratingId of the rating, which be deleted
     */
    @Override
    public void deleteById(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }

    /**
     * delete a set of rating from the database
     *
     * @param ratings that would be deleted from the database
     */
    @Override
    public void deleteAll(Set<Rating> ratings) {
        Set<Rating> deleted_rating = new HashSet<>();
        ratingRepository.deleteAll(deleted_rating);

    }
}
