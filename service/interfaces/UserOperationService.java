package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.model.User;

/**
 * Service interface for managing user´s operations
 * It provides an additional methods that done by the User related to content and rating.
 *
 * @author Mohamad Alkasem
 *
 */
public interface UserOperationService  {



    // ############### User --> Content ##############
/*
    void addContentToUser(Long userId, Long contentId);
    void removeContentByUser( Long userId, Long contentId);

    void updateContentByUser( Long userId, Long contentId, String newContent);
*/


    // ############## User --> Rating ################

    void removeRatingFromUser(Long userId, Long ratingId);
   // void updateContentRating(Long userId, Long contentId, int newRating);
  //  void addRatingByUser(Long userId, Long ratingId);


    // ##################################################
    Rating createRatingByUser(Long userId, Long contentId, int rating);
}
