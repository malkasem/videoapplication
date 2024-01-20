package encoway.springframework.videoapplication.service.implementation;

import encoway.springframework.videoapplication.model.Movie;
import encoway.springframework.videoapplication.repository.MovieRepository;
import encoway.springframework.videoapplication.service.interfaces.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the MovieService interface.
 * <p>
 * This class provides the concrete implementation of the MovieService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected and
 * instantiated by Spring's component scanning.
 * <p>
 * It uses  a RatingRepository to perform operations related to ratings.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.MovieService
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private final MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * @param movieId is the ID of the movie to be found
     * @return the movie with the given id or null if it's not exists
     * @method find the movie in the database by its id
     */
    @Override
    public Optional<Movie> findById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    /**
     * @method find set of movie in the database
     * @return set of movie or null
     */
    @Override
    public Set<Movie> findAll() {
        Set<Movie> movies = new HashSet<>();
        movieRepository.findAll().forEach(movie -> movies.add(movie));
        return movies;
    }

    /**
     * @method saves movie to the database
     * @param movie of the entity Movie
     * @return the saved movie
     */
    @Override
    public Movie save(Movie movie) {
        return movieRepository.save(movie);
    }

    /**
     * saves a set of movies to teh database
     *
     * @param movies set to be found
     * @return set of the movies or null
     */
    @Override
    public Set<Movie> saveAll(Set<Movie> movies) {
        Set<Movie> saved_movies = new HashSet<>();
        movieRepository.saveAll(movies).forEach(saved_movies::add);
        return saved_movies;
    }

    /**
     * delete a movie from the database
     *
     * @param movie which would be deleted from the database
     */
    @Override
    public void delete(Movie movie) {
        movieRepository.delete(movie);
    }

    /**
     * delete a movie from the database by its id
     *
     * @param movieId of the movie, which be deleted
     */
    @Override
    public void deleteById(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    /**
     * delete a set of movies from the database
     *
     * @param movies that would be deleted from the database
     */
    @Override
    public void deleteAll(Set<Movie> movies) {
        movieRepository.deleteAll(movies);
    }
}
