package encoway.springframework.videoapplication.service.implementation;

import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.repository.ContentRepository;
import encoway.springframework.videoapplication.repository.RatingRepository;
import encoway.springframework.videoapplication.repository.UserRepository;
import encoway.springframework.videoapplication.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of the UserService interface.
 * <p>
 * This class provides the concrete implementation of the UserService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected and
 * instantiated by Spring's component scanning.
 * <p>
 * It uses a UserRepository, a ContentRepository and a RatingRepository to perform operations related to Users.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.UserService
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private final UserRepository userRepository;

    private final ContentRepository contentRepository;
    private final RatingRepository ratingRepository;

    /**
     * constructs a new UserServiceImpl object with the parameters:
     *
     * @param userRepository    to be used for user-related operations
     * @param contentRepository to be used for content-related operations
     * @param ratingRepository  to be used for rating-related operations
     */

    public UserServiceImpl(UserRepository userRepository, ContentRepository contentRepository, RatingRepository ratingRepository) {
        this.userRepository = userRepository;
        this.contentRepository = contentRepository;
        this.ratingRepository = ratingRepository;
    }


    /**
     * @param userId is the ID of the user to be found
     * @return user with the given id or null if it's not exists
     * @method find the user in the database by its id
     */
    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    /**
     * @return set of users or null
     * @method find set of users in the database
     */
    @Override
    public Set<User> findAll() {
        Set<User> users = new HashSet<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    /**
     * @param user of the entity
     * @return the saved user
     * @method saves user to the database
     */
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    /**
     * saves a set of users to teh database
     *
     * @param users set of entities to be found
     * @return set of the users or null
     */
    @Override
    public Set<User> saveAll(Set<User> users) {
        Set<User> saved_users = new HashSet<>();
        userRepository.saveAll(users).forEach(saved_users::add);
        return saved_users;
    }

    /**
     * delete a user from the database
     *
     * @param user object which would be deleted from the database
     */
    @Override
    public void delete(User user) {
        userRepository.delete(user);
    }

    /**
     * delete an object from the database by its id
     * @param userId of the user, which be deleted
     */
    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    /**
     * delete a set of objects from the database
     *
     * @param users that would be deleted from the database
     */
    @Override
    public void deleteAll(Set<User> users) {
        userRepository.deleteAll(users);
    }


    // ##################################################################################################################
    @Override
    public User findByUserNameOrEmail(String userName, String email) {
        return userRepository.findByUserNameOrEmail(userName, email);
    }

    /**
     * @param userName string of the user to be created
     * @param email of user to be created
     * @return a new user
     */
    @Override
    public User createUser(String userName, String email) {
        if (userName == null || email == null) {
            throw new IllegalArgumentException("Invalid Input Data!");
        }
        User existingUser = userRepository.findByUserNameOrEmail(userName, email);
        if(existingUser != null){
            throw new IllegalArgumentException("User Is Already Exist!");
        }
        User newUser = new User();
        newUser.setUserName(userName);
        newUser.setEmail(email);
        return userRepository.save(newUser);
    }

}
