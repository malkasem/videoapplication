package encoway.springframework.videoapplication.service.implementation;

import encoway.springframework.videoapplication.model.Genre;
import encoway.springframework.videoapplication.repository.GenreRepository;
import encoway.springframework.videoapplication.service.interfaces.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


/**
 * Implementation of the GenreService interface.
 * <p>
 * This class provides the concrete implementation of the GenreService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected and
 * instantiated by Spring's component scanning.
 * <p>
 * It uses  a GenreRepository to perform operations related to genres.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.GenreService
 */

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    private final GenreRepository genreRepository;

    /**
     * constructs a new GenreServiceImpl object with the parameter
     * @param genreRepository to be used for the genre`s operations
     */
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    /**
     * @method find the genre in the database by its id
     * @param genreId is the ID of the genre to be found
     * @return the genre with the given id or null if it's not exists
     */
    @Override
    public Optional<Genre> findById(Long genreId) {
        return genreRepository.findById(genreId);
    }

    /**
     * @method find set of genre in the database
     * @return set of genre or null
     */
    @Override
    public Set<Genre> findAll() {
        Set<Genre> genres = new HashSet<>();
        genreRepository.findAll().forEach(genre -> genres.add(genre));
        return genres;
    }

    /**
     * @method saves genre to the database
     * @param genre of the entity
     * @return the saved genre
     */
    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    /**
     * saves a set of genres to teh database
     *
     * @param genres set of entities to be found
     * @return set of the saved_genres or null
     */
    @Override
    public Set<Genre> saveAll(Set<Genre> genres) {
        Set<Genre> saved_genres = new HashSet<>();
        genreRepository.saveAll(genres).forEach(genre -> saved_genres.add(genre));
        return saved_genres;
    }

    /**
     * delete a genre from the database
     *
     * @param genre which would be deleted from the database
     */
    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);

    }

    /**
     * delete a genre from the database by its id
     *
     * @param genreId of the genre, which be deleted
     */
    @Override
    public void deleteById(Long genreId) {
        genreRepository.deleteById(genreId);
    }

    /**
     * delete a set of objects from the database
     *
     * @param objects that would be deleted from the database
     */
    @Override
    public void deleteAll(Set<Genre> objects) {
        Set<Genre> deleted_genres = new HashSet<>();
        genreRepository.deleteAll(deleted_genres);
    }
}
