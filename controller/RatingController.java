package encoway.springframework.videoapplication.controller;

import encoway.springframework.videoapplication.dto.ContentDTO;
import encoway.springframework.videoapplication.dto.RatingDTO;
import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.service.implementation.RatingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;
@RestController
@RequestMapping(value = "/api")
@Tag(name = "Rating")
public class RatingController {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private  final RatingServiceImpl ratingService;

    public RatingController(ModelMapper modelMapper, RatingServiceImpl ratingService) {
        this.modelMapper = modelMapper;
        this.ratingService = ratingService;
    }

    /**
     *
     * @param ratingId id of the rating to be requested
     * @return ResponseEntity with ratingDto
     */

    @Operation(
            description = "Get Rating By Id",
            summary = "Get Rating By Id",
            responses = {
                    @ApiResponse(
                            description = "Rating Successfully Found!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Rating To Be Found!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Bad Request!",
                            responseCode = "400"
                    )
            }
    )
    @GetMapping("/ratingId")
    public ResponseEntity<RatingDTO> findRatingById(@RequestBody Long ratingId ){
        Rating rating = ratingService.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("No Rating With ID: ", ratingId));
        if(rating != null){

            RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
            return ResponseEntity.ok(ratingDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @Operation(
            description = "Get Rating All Ratings",
            summary = "Get Rating By Id",
            responses = {
                    @ApiResponse(
                            description = "Rating Successfully Called!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Rating To Be Found!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Bad Request!",
                            responseCode = "400"
                    )
            }
    )

    /**
     *
     * @return set of all ratings
     */
    @GetMapping("/ratings")
    public ResponseEntity<Set<RatingDTO>> getAllRatings() {
        Set<Rating> ratings = ratingService.findAll();
        Set<RatingDTO> ratingDTOSet = ratings.stream()
                .map(rating -> modelMapper.map(rating, RatingDTO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok().body(ratingDTOSet);
    }


    @Operation(
            description = "Create A New Rating",
            summary = "Rating New User",
            responses = {
                    @ApiResponse(
                            description = "A New Rating Successfully Created!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Rating Is Already Exists!",
                            responseCode = "403"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    )
            }
    )
    /**
     *
     * @param ratingDTO the rating to be created
     * @return a new rating dto
     */
    @PostMapping("/ratings")
    public ResponseEntity<RatingDTO> createRating(@RequestBody RatingDTO ratingDTO){
        Rating rating = modelMapper.map(ratingDTO, Rating.class);
        Rating createdRating = ratingService.save(rating);


        // convert Rating entity to rating DTO
        RatingDTO createdRatingDTO = modelMapper.map(createdRating, RatingDTO.class);


        return new ResponseEntity<RatingDTO>(createdRatingDTO, HttpStatus.CREATED);
    }

    @Operation(
            description = "Update Info Of Rating",
            summary = "Update Rating",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Rating Not Found!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Bad Request",
                            responseCode = "400"
                    )
            }
    )
    /**
     *
     * @param ratingId id of user to be updated
     * @param ratingDTO content to be updated
     * @return ResponseEntity with updated content dto
     */
    @PutMapping("/ratings")
    public ResponseEntity<RatingDTO> updateRating(@RequestBody Long ratingId, @RequestBody RatingDTO ratingDTO) {

        Rating rating = ratingService.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("No Rating With ID: ", ratingId));

        if (rating != null) {

            rating.setUser(ratingDTO.getUser());
            rating.setContent(ratingDTO.getContent());
            Rating updatedRating = ratingService.save(rating);

            RatingDTO updatedRatingDTO = modelMapper.map(updatedRating, RatingDTO.class);

            return ResponseEntity.ok().body(updatedRatingDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            description = "Delete Rating By Id ",
            summary = "Delete Rating By Id",
            responses = {
                    @ApiResponse(
                            description = "Rating Successfully Deleted!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Rating With This Id!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Rating Could Not be Deleted",
                            responseCode = "400"
                    )
            }
    )

    /**
     * Endpoint to deleted a rating
     * @param ratingId the id of rating to be deleted
     * @return ResponseEntity with no rating
     */

    @DeleteMapping("/ratingId")

    public ResponseEntity<Void> deleteRating(@RequestBody Long ratingId) {
        Rating rating = ratingService.findById(ratingId)
                .orElseThrow(() -> new ResourceNotFoundException("No Rating With ID: ", ratingId));
        if(rating == null) {
            return ResponseEntity.notFound().build();
        } else {
            ratingService.delete(rating);
        }
        return ResponseEntity.noContent().build();
    }

}
