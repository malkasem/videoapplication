package encoway.springframework.videoapplication.service.implementation;


import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.repository.ContentRepository;
import encoway.springframework.videoapplication.repository.RatingRepository;
import encoway.springframework.videoapplication.service.interfaces.ContentService;
import encoway.springframework.videoapplication.service.interfaces.RatingService;
import encoway.springframework.videoapplication.service.interfaces.UserOperationService;
import encoway.springframework.videoapplication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of the UserOperationService interface.
 * <p>
 * This class provides the concrete implementation of the UserOperationService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected
 * <p>
 * It uses a UserService, a ContentService and a RatingService to perform operations of contents and ratings related to users.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.UserOperationService
 *
 * @author Mohamad Alkasem
 */


@Service
public class UserOperationServiceImpl implements UserOperationService {

    @Autowired
    private final UserService userService;
    @Autowired
    private final ContentService contentService;
    @Autowired
    private final RatingService ratingService;



    /**
     *
     * @param userService related to user
     * @param contentService related to content
     * @param ratingService related to ratings
     */
    public UserOperationServiceImpl(UserService userService, ContentService contentService, RatingService ratingService) {
        this.userService = userService;
        this.contentService = contentService;
        this.ratingService = ratingService;


    }

    /**
     * @param userId ID of the  user whose content to be added
     * @param contentId ID of content to be added to DB
     */
/*
    @Override
    public  void addContentToUser(Long userId, Long contentId) {
        User user = userService.findById(userId);
        Content content = contentService.findById(contentId);
        if (user == null) {
            throw new IndexOutOfBoundsException("No User with this ID : %s" + userId);
        } else if (content == null) {
            throw new IndexOutOfBoundsException("No Content with this ID : %s" + contentId);
        }
        else {
            user.getContents().add(content);
            userService.save(user);
        }
    }*/

    /**
     * @param userId ID of user whose content to be removed
     * @param contentId ID of content which be removed from DB
     */
/*
    @Override
    public void removeContentByUser(Long userId, Long contentId) {
        User user = userService.findById(userId);
        Content content = contentService.findById(contentId);
        if(user != null && content != null ){
            user.getContents().remove(content);
            userService.save(user);
        }
    }
*/

    /**
     *
     * this method represents the updating the content related to the user
     *
     * @param userId ID user whose content to be updated
     * @param contentId ID content would be updated
     * @param newContentTitle the new content would be added
     */
/*    @Override
    public void updateContentByUser(Long userId, Long contentId, String newContentTitle) {
        User user = userService.findById(userId);
        Content content = contentService.findById(contentId);
        if ( user != null && content != null &&  newContentTitle != null) {
            // fetch the content from DB
            Content existingContent = contentService.findById(content.getContentId());
            if(existingContent != null) {
                existingContent.setTitle(newContentTitle);
                contentService.save(existingContent);
                // update the user´s content
                user.getContents().remove(content);
                user.getContents().add(existingContent);
                userService.save(user);
            }
        }
    }*/

    /**
     * this method represents the rating associate with content and user
     *
     * @param userId ID of user whose content and rating which would be updated
     * @param contentId ID of content related to the rating
     * @param newRating the new rating by the user
     */
/*    @Override
    public void updateContentRating(Long userId, Long contentId, int newRating) {
        User user = userService.findById(userId);
        Content content = contentService.findById(contentId);
        if (user != null && content != null) {
            Rating userRating = user.getRatings().stream()
                    .filter(rating -> rating.getContent().equals(content))
                    .findFirst()
                    .orElse(null);
            if (userRating != null) {
                userRating.setRating(newRating);
                ratingService.save(userRating);
            }
        }
    }*/

    /**
     *
     * @param userId
     * @param ratingId
     */
/*    @Override
    public void addRatingByUser(Long userId, Long ratingId) {
        User user = userService.findById(userId);
        Rating rating = ratingService.findById(ratingId);
        if(user != null && rating != null) {
            user.getRatings().add(rating);
            userService.save(user);
        }
    }*/
    /**
     * @param userId the user who add a rating
     * @param ratingId the rating related to the user
     */
    @Override
    public void removeRatingFromUser(Long userId, Long ratingId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No User With ID: ", userId));
        Rating rating = ratingService.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("No Rating With ID: ", ratingId));
        if( user != null && rating != null) {
            user.getRatings().remove(rating);
            userService.save(user);
        }
    }

    // ################################

@Override
    public Rating createRatingByUser(Long userId, Long contentId, int rating){
        if (rating <= 0 || rating > 5) {
            throw new IllegalArgumentException("Rating Must Be Between 1 Und 5 !");
        }

        Content content = contentService.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("No Movie With ID: ", contentId));
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No User With ID: ", userId));
        Rating newRating = new Rating();
        newRating.setRating(rating);
        newRating.setContent(content);
        newRating.setUser(user);

       return ratingService.save(newRating);
    }

}
