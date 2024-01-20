package encoway.springframework.videoapplication.controller;

import encoway.springframework.videoapplication.dto.ContentDTO;
import encoway.springframework.videoapplication.dto.GenreDTO;
import encoway.springframework.videoapplication.dto.RatingDTO;
import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.Genre;
import encoway.springframework.videoapplication.model.Rating;
import encoway.springframework.videoapplication.service.implementation.GenreServiceImpl;
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
@Tag(name = "Genre")
public class GenreController {

    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final GenreServiceImpl genreService;

    public GenreController(ModelMapper modelMapper, GenreServiceImpl genreService) {
        this.modelMapper = modelMapper;
        this.genreService = genreService;
    }


    @Operation(
            description = "Find Genre With Id",
            summary = "Find Genre By Id",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Genre Not Found",
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
     * @param genreId id of the genre to be requested
     * @return ResponseEntity with genre dto
     */
    @GetMapping("/genreId")
    public ResponseEntity<GenreDTO> findGenreById(@RequestBody Long genreId ){
        Genre genre = genreService.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("No Genre With ID: ", genreId));
        if(genre != null){

            GenreDTO genreDTO = modelMapper.map(genre, GenreDTO.class);
            return ResponseEntity.ok(genreDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @Operation(
            description = "Get Genre All Ratings",
            summary = "Get Genre By Id",
            responses = {
                    @ApiResponse(
                            description = "Genre Successfully Called!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Genre To Be Found!",
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
     * @return set of all genres
     */
    @GetMapping("/genres")
    public ResponseEntity<Set<GenreDTO>> getAllGenres() {
        Set<Genre> genres = genreService.findAll();
        Set<GenreDTO> genreDTOSet = genres.stream()
                .map(genre -> modelMapper.map(genre, GenreDTO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok().body(genreDTOSet);
    }


    @Operation(
            description = "Create A New Genre",
            summary = "Create New Genre",
            responses = {
                    @ApiResponse(
                            description = "A New Genre Successfully Created!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Genre Is Already Exists!",
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
     * @param genreDTO the genre to be created
     * @return a new genre dto
     */
    @PostMapping("/genres")
    public ResponseEntity<GenreDTO> createGenre(@RequestBody GenreDTO genreDTO){
        Genre genre = modelMapper.map(genreDTO, Genre.class);
        Genre createdGenre = genreService.save(genre);

        GenreDTO createdGenreDTO = modelMapper.map(createdGenre, GenreDTO.class);


        return new ResponseEntity<GenreDTO>(createdGenreDTO, HttpStatus.CREATED);
    }

    @Operation(
            description = "Update Info Of Genre",
            summary = "Update Genre",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Genre Not Found!",
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
     * @param id id of genre to be updated
     * @param genreDTO genre to be updated
     * @return ResponseEntity with updated genre dto
     */
    @PutMapping("/genre")
    public ResponseEntity<GenreDTO> updateGenre(@RequestBody Long id, @RequestBody GenreDTO genreDTO) {

        Genre genre = genreService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No Genre With ID: ", id));

        if (genre != null) {

           genre.setGenreType(genreDTO.getGenreType());
           genre.setContents(genreDTO.getContents());
            Genre updatedGenre = genreService.save(genre);

            GenreDTO updatedGenreDTO = modelMapper.map(updatedGenre, GenreDTO.class);

            return ResponseEntity.ok().body(updatedGenreDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            description = "Delete Genre By Id ",
            summary = "Delete Genre By Id",
            responses = {
                    @ApiResponse(
                            description = "Genre Successfully Deleted!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Genre With This Id!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Genre Could Not be Deleted",
                            responseCode = "400"
                    )
            }
    )

    /**
     * Endpoint to deleted a genre
     * @param genreId the id of genre to be deleted
     * @return ResponseEntity with no genre
     */

    @DeleteMapping("/genreId")

    public ResponseEntity<Void> deleteGenre(@RequestBody Long genreId) {
        Genre genre = genreService.findById(genreId)
                .orElseThrow(() -> new ResourceNotFoundException("No Genre With ID: ", genreId));
        if(genre == null) {
            return ResponseEntity.notFound().build();
        } else {
            genreService.delete(genre);
        }
        return ResponseEntity.noContent().build();
    }
}
