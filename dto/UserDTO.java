package encoway.springframework.videoapplication.dto;

import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Genre;
import encoway.springframework.videoapplication.model.Rating;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * This Class represents the Data Transfer Object DTO of the User entity
 * <p>
 * DTO is an Object which transfer the Data between the layers. So in the User case,
 * the UserDTO will encapsulate the Data of the User properties in one single obj.
 * </p>
 *
 * @author Mohamad Alkasem
 * @version 1.0
 */

@Data
@NoArgsConstructor
public class UserDTO {
    /**
     * The identifier and other properties of the User
     */
    private Long userId;
    private String userName;
    private String email;
    private Genre favoriteGenre;
    private Set<Content> contents = new HashSet<>();
    private Set<Rating> ratings = new HashSet<>();
}
