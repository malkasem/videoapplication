package encoway.springframework.videoapplication.controller;

import encoway.springframework.videoapplication.dto.ContentDTO;
import encoway.springframework.videoapplication.dto.UserDTO;
import encoway.springframework.videoapplication.exception.ResourceNotFoundException;
import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.model.User;
import encoway.springframework.videoapplication.service.implementation.ContentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * ContentController represents the Controller of the Content in this Spring application
 * it handles the Http requests and response related to the Content´s operations
 *
 * @author Mohamad Alkasem
 */
@RestController
//@RequestMapping(value = {"/api/content", "/contents"})
@RequestMapping(value = "/api")
@Tag(name = "Content")
public class ContentController {
    @Autowired
    private final ModelMapper modelMapper;
    @Autowired
    private final ContentServiceImpl contentService;

    /**
     *
     * @param modelMapper ModelMapper
     * @param contentService the implementations of services related to contents
     */
    public ContentController(ModelMapper modelMapper, ContentServiceImpl contentService) {
        this.modelMapper = modelMapper;
        this.contentService = contentService;
    }

    @Operation(
            description = "Get Content By Id",
            summary = "Get Content By Id",
            responses = {
                    @ApiResponse(
                            description = "Content Successfully Called!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Content To Be Found!",
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
     * @param contentId id of the content to be requested
     * @return ResponseEntity with contentDto
     */
    @GetMapping("/contentId")
    public ResponseEntity<ContentDTO> findContentById(@RequestBody Long contentId ){
        Content content = contentService.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("No Movie With ID: ", contentId));
        if(content != null){

            ContentDTO contentDTO = modelMapper.map(content, ContentDTO.class);
            return ResponseEntity.ok(contentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
    @Operation(
            description = "Get  All Content",
            summary = "Get All Contents",
            responses = {
                    @ApiResponse(
                            description = "Content Successfully Called!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Content To Be Found!",
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
     * @return set of all contents
     */
    @GetMapping("/contents")
    public ResponseEntity<Set<ContentDTO>> getAllContent() {
        Set<Content> contents = contentService.findAll();
        Set<ContentDTO> contentDTOSet = contents.stream()
                .map(content -> modelMapper.map(content, ContentDTO.class))
                .collect(Collectors.toSet());
        return ResponseEntity.ok().body(contentDTOSet);
    }



    @Operation(
            description = "Create A New Content",
            summary = "Create New Content",
            responses = {
                    @ApiResponse(
                            description = "A New Content Successfully Created!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Content Is Already Exists!",
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
     * @param contentDTO the content to be created
     * @return a new content dto
     */
    @PostMapping("/contents")
    public ResponseEntity<ContentDTO> createContent(@RequestBody ContentDTO contentDTO){

        Content content = modelMapper.map(contentDTO, Content.class);
        Content createdContent = contentService.save(content);

        // convert user entity to user DTO
        ContentDTO createdContentDTO = modelMapper.map(createdContent, ContentDTO.class);

        return new ResponseEntity<ContentDTO>(createdContentDTO, HttpStatus.CREATED);
    }


    @Operation(
            description = "Update Info Of Content",
            summary = "Update Content",
            responses = {
                    @ApiResponse(
                            description = "Success",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Content Not Found!",
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
     * @param contentId id of user to be updated
     * @param contentDTO content to be updated
     * @return ResponseEntity with updated content dto
     */
    @PutMapping("/contents")
    public ResponseEntity<ContentDTO> updateContent(@RequestBody Long contentId, @RequestBody ContentDTO contentDTO) {

        Content content = contentService.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("No Movie With ID: ", contentId));

        if (content != null) {

            content.setTitle(contentDTO.getTitle());
            content.setGenres(contentDTO.getGenres());
            content.setRatings(contentDTO.getRatings());

            Content updatedContent = contentService.save(content);

            ContentDTO updatedContentDTO = modelMapper.map(updatedContent, ContentDTO.class);
            return ResponseEntity.ok().body(updatedContentDTO);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(
            description = "Delete Content By Id ",
            summary = "Delete Content By Id",
            responses = {
                    @ApiResponse(
                            description = "Content Successfully Deleted!",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "No Content With This Id!",
                            responseCode = "404"
                    ),
                    @ApiResponse(
                            description = "Content Could Not be Deleted",
                            responseCode = "400"
                    )
            }
    )
    /**
     * Endpoint to delete a content
     * @param contentId the id of Content to be deleted
     * @return ResponseEntity with no content
     */

    @DeleteMapping("/contentId")

    public ResponseEntity<Void> deleteContent(@RequestBody Long contentId) {
        Content content = contentService.findById(contentId)
                .orElseThrow(() -> new ResourceNotFoundException("No Movie With ID: ", contentId));
        if(content == null) {
            return ResponseEntity.notFound().build();
        } else {
            contentService.delete(content);
        }
        return ResponseEntity.noContent().build();
    }

}
