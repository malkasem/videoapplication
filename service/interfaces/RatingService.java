package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.dto.ContentDTO;
import encoway.springframework.videoapplication.dto.RatingDTO;
import encoway.springframework.videoapplication.dto.UserDTO;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.service.BaseService;

/**
 * Service interface for managing ratings.
 * This interface extends the BaseService interface with Rating as the entity type and Long as the ID type.
 *
 * @author Mohamad Alkasem
 *
 * @see encoway.springframework.videoapplication.service.BaseService
 */
public interface RatingService extends BaseService<Rating, Long> {
  //  void createRatingByUser(Long userId,Long contentId, int rating);

}
