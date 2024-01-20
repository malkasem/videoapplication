
package encoway.springframework.videoapplication.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.HashSet;
import java.util.Set;

/**
 * this represents the Genre Entity
 * Genre is mapped to the "gerne" table in the database
 * @author Mohamad Alaksem
 * @vison 1.1
 */

@Entity
@Data
@Table(name = "genre")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Genre {

    /**
     * the unique Id of the genres
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long genreId;
    @Column(name = "genreType")
    private String genreType;

    /**
     * the contents that related to one or many genres
     */
    @ManyToMany(mappedBy = "genres")
    private Set<Content> contents = new HashSet<>();

}
