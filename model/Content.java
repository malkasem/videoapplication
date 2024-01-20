package encoway.springframework.videoapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * it represents the Content entity in Javaflix video-Application
 * Content entity is mapped to "content" table in the Database
 * @author Mohamad Alkasem
 * @version 1.1
 */

@Entity
@Data
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "content_type", discriminatorType = DiscriminatorType.STRING)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public abstract class Content {

    /**
     * id is unique ID of the Content entity
     * title of the content
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contentId;
    @Column(name = "title")
    private String title;


    /**
     * the Ratings related to the content
     * On-To-Many is the relationship with the Rating entity, which composited part of its content
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "content")
    private Set<Rating> ratings = new HashSet<>();

    /**
     * the Genre associated to the Content
     * Many-ToMany is the relationship with the Genre entity
     * the relationship content and its genre is mapped to "content_genre" table in the database
     */
    @ManyToMany
    @JoinTable(name = "content_genre",
            joinColumns = { @JoinColumn(name = "contentId")},
            inverseJoinColumns = {@JoinColumn(name = "genreId")}
    )
    private Set<Genre> genres = new HashSet<>();

}