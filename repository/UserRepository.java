package encoway.springframework.videoapplication.repository;

import encoway.springframework.videoapplication.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * represents the User persistence, which will act as database repository for the user
 * @autor Mohamad Alkasem
 * @version 1.0
 */

/**
 * CrudRepository<T,ID> is an interface that extends the Spring Data Repository
 * it provides the CRUD methods in performing on the entities in relational with database
 * Repository manages entity User, which has type of @Id Long
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User  findByUserNameOrEmail(String userName, String email);
}
