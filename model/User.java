package encoway.springframework.videoapplication.model;
import jakarta.persistence.*;
import lombok.*;
import java.util.HashSet;
import java.util.Set;

/**
 * it represents the User entity in Javaflix video-Application
 * User entity is mapped to "users" table in the Database
 * @author Mohamad Alkasem
 * @version 1.1
 */
@Entity
@Data
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    /**
     * the unique Id of the users
     * the name of the user
     * the E-Mail of the user
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(name = "userName")
    private String userName;
    @Column(name = "email")
    private String email;


    /**
     * Rating which is done by the users
     * One-To_Many and cascade is the relationship with the Rating entity
     */
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private Set<Rating> ratings = new HashSet<>();


}
