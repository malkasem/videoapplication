package encoway.springframework.videoapplication.dto;

import encoway.springframework.videoapplication.model.Genre;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * This Class represents the Data Transfer Object DTO of the Content entity
 *
 * @author Mohamad Alkasem
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class ContentDTO {
    /**
     * identifier and properties of the Content
     */
    private Long contentId ;
    private String title;
    private User user;

    private Set<Rating> ratings = new HashSet<>();

    private Set<Genre> genres = new HashSet<>();
}
