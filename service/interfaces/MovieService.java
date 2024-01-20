package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.model.Movie;
import encoway.springframework.videoapplication.service.BaseService;

/**
 * Service interface for managing movies.
 * This interface extends the BaseService interface with Movie as the entity type and Long as the ID type.
 *
 * @author Mohamad Alkasem
 *
 * @see encoway.springframework.videoapplication.service.BaseService
 */
public interface MovieService extends BaseService<Movie, Long> {
}
