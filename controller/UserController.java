package encoway.springframework.videoapplication.controller;

import encoway.springframework.videoapplication.dto.ContentDTO;
import encoway.springframework.videoapplication.dto.UserDTO;
import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.service.interfaces.UserOperationService;
import encoway.springframework.videoapplication.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * UserController represents the Controller of the User in this Spring application
 * it handles the Http requests and response related to the User´s operations
 *
 * @author Mohamad Alkasem
 */
@RestController
@RequestMapping( value = "/api")
public class UserController {

    /**
     * injection of the UserServiceImpl
     */
    @Autowired
    private final UserOperationService userOperationService;
    @Autowired
    private final UserService userService;


    /**
     * <p>
     * injection of the class ModelMapper for the mapping the objects between UserDOT and User entity
     * this will reduce the manual mapping code
     * </p>
     */
    @Autowired
    private final ModelMapper modelMapper;

    /**
     * Constructor of the UserController
     *
     * @param userOperationService
     * @param userService          the implementation of services related User
     * @param modelMapper          ModelMapper
     */
    public UserController(UserOperationService userOperationService, UserService userService, ModelMapper modelMapper) {
        this.userOperationService = userOperationService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    /**
     * Endpoint to request all users
     * @return ResponseEntity with a Set of all users DTO.
     */
    @GetMapping("/users")
    public ResponseEntity<Set<UserDTO>> getAllUsers() {

        Set<User> users = userService.findAll();
        Set<UserDTO> userDTOSet = users.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok().body(userDTOSet);
    }
    /**
     * Endpoint to request user with it´s id
     * @param userId the id of the user
     * @return ResponseEntity with user DTO
     */
    @GetMapping("userId")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long userId){
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No User With ID: ", userId));;

        if (user != null) {

            // convert user entity to DTO
            UserDTO userDTO = modelMapper.map(user, UserDTO.class);
            return ResponseEntity.ok().body(userDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
    /**
     * Endpoint to create a new User.
     * @param userDTO the User which be created
     * @return ResponseEntity with the created User
     */
    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO){

        // convert userDTO to user entity
        User user = modelMapper.map(userDTO, User.class);
        User createdUser = userService.save(user);

        // convert user entity to user DTO
        UserDTO createdUserDTO = modelMapper.map(createdUser, UserDTO.class);
        return new ResponseEntity<UserDTO>(createdUserDTO, HttpStatus.CREATED);
    }

    /**
     * Endpoint to update the User
     * @param userId id of the user to be updated
     * @param userTDO the user to be updated
     * @return ResponseEntity with updated User
     */
    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@RequestBody Long userId, @RequestBody UserDTO userTDO){

        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No User With ID: ", userId));
        if (user != null) {
            user.setUserName(userTDO.getUserName());
            user.setEmail(userTDO.getEmail());
            user.setRatings(userTDO.getRatings());

            User updatedUser = userService.save(user);

            UserDTO updatedUserDTO = modelMapper.map(updatedUser, UserDTO.class);
            return ResponseEntity.ok().body(updatedUserDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Endpoint to delete a User
     * @param userId the id of User to be deleted
     * @return ResponseEntity with no content
     */
    @DeleteMapping("/userId")
    public ResponseEntity<Void> deleteUser(@RequestParam Long userId) {
        User user = userService.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("No User With ID: ", userId));
        if(user == null) {
            return ResponseEntity.notFound().build();
        } else {
            userService.delete(user);
        }
        return ResponseEntity.noContent().build();
    }



   /* ################## USER OPERATIONS ########################*/

    @PostMapping("/{userId}/rating/{contentId}")
    public ResponseEntity<Rating> createRatingByUser(@PathVariable Long userId, @PathVariable Long contentId, @RequestBody int newRating) {
        userOperationService.createRatingByUser(userId, contentId, newRating);
        return new ResponseEntity<>(HttpStatus.OK);
    }
/*
    @PostMapping("/{userId}/content/{contentId}")
    public ResponseEntity<Void> addContentToUser(@PathVariable Long userId, @PathVariable Long contentId) {
        userOperationService.addContentToUser(userId, contentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{userId}/content/{contentId}")
     public  ResponseEntity<Void> removeContentByUser(@PathVariable Long userId, @PathVariable Long contentId) {
        userOperationService.removeContentByUser(userId, contentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{userId}/content/{contentId}")
    public ResponseEntity<Void> updateContentByUser(@PathVariable Long userId, @PathVariable Long contentId, @RequestBody ContentDTO newContentDTO) {

        if( newContentDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            //System.out.println("NewContentDTO " + newContentDTO);
            Content newContent = modelMapper.map(newContentDTO, Content.class);
            //System.out.println("NewContentDTO " + newContent);
            userOperationService.updateContentByUser(userId, contentId, newContent.getTitle());
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @PostMapping("/{userId}/rating/{ratingId}")
    public ResponseEntity<Void> addRatingByUser(@PathVariable Long userId, @PathVariable Long ratingId){
        userOperationService.addRatingByUser(userId, ratingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @DeleteMapping("/{userId}/rating/{ratingId}")
    public  ResponseEntity<Void> removeRatingFromUser(@PathVariable Long userId, @PathVariable Long ratingId) {
        userOperationService.removeRatingFromUser(userId, ratingId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

   @PutMapping("/{userId}/rating/{contentId}")
    public  ResponseEntity<Void> updateContentRating(@PathVariable Long userId, @PathVariable Long contentId, @RequestBody int newRating) {
        userOperationService.updateContentRating(userId, contentId, newRating);
        return  new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/adduser")
    public User addNewUser(@RequestBody User user) {
        return userService.addUser(user.getUserName(), user.getEmail());

    }*/
}
