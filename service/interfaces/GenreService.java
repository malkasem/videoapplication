package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.model.Genre;
import encoway.springframework.videoapplication.service.BaseService;


/**
 * Service interface for managing genres.
 * This interface extends the BaseService interface with Genre as the entity type and Long as the ID type.
 *
 * @author Mohamad Alkasem
 *
 * @see encoway.springframework.videoapplication.service.BaseService
 */
public interface GenreService extends BaseService<Genre, Long> {
}
