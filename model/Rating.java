package encoway.springframework.videoapplication.model;


import jakarta.persistence.*;
import lombok.*;

/**
 * this model represents the Rating-entity
 * Rating entity is mapped to the "ratings" table in database
 * @author Moahamad Alkasem
 * @version 1.1
 */
@Entity(name = "ratings")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Rating {

    /**
     * unique ID of the Rating entity
     * rating is the level of the user rating
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;
    private int rating;

    /**
     * the use related to the rating
     * Many-To-One is the relationship with the User entity
     */
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    /**
     * the content related to the rating
     * Many-To-One is relationship with the Content entity
     */
    @ManyToOne
    @JoinColumn(name = "contentId")
    private Content content;

}