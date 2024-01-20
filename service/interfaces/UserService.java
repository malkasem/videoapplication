package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.service.BaseService;

/**
 * Service interface for managing users.
 * This interface extends the BaseService interface with User as the entity type and Long as the ID type.
 * It provides an additional methods that done by the User
 *
 * @author Mohamad Alkasem
 *
 * @see encoway.springframework.videoapplication.service.BaseService
 */
public interface UserService extends BaseService<User, Long> {

    User findByUserNameOrEmail(String userName, String email);

    User createUser(String userName, String email);
}
