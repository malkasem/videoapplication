package encoway.springframework.videoapplication.dto;

import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This Class represents the Data Transfer Object DTO of the Rating entity
 *
 * @author Mohamad Alkasem
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class RatingDTO {

    private Long ratingId;
    private int rating;
    private User user;
    private Content content;
}
