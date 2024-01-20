package encoway.springframework.videoapplication.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.*;

/**
 * represents the Movie Entity
 * its type of the Content entity
 * Movie entity is mapped to as String to the "content-type" column in the database
 * @see Content "content-type" is part of the table "contents" in teh database
 * @author Mohamad Alaksem
 * @version 1.1
 */
@Entity
@Data
@DiscriminatorValue("movie")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Movie extends Content{

    /**
     * refers to the duration time of Movie entity
     * it´s mapped to the column "duration" which is part of the "contents" table in database
     */
    private int duration;
}
