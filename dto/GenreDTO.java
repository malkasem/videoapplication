package encoway.springframework.videoapplication.dto;

import encoway.springframework.videoapplication.model.Content;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * This Class represents the Data Transfer Object DTO of the Genre entity
 *
 * @author Mohamad Alkasem
 * @version 1.0
 */
@Data
@NoArgsConstructor
public class GenreDTO {

    /**
     * identifier and properties of the Genre
     */
    private Long genreId;
    private String genreType;
    private Set<Content> contents = new HashSet<>();

}
