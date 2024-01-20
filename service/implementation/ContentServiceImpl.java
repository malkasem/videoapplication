package encoway.springframework.videoapplication.service.implementation;

import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.repository.ContentRepository;
import encoway.springframework.videoapplication.service.BaseService;
import encoway.springframework.videoapplication.service.interfaces.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
/**
 * @author Mohamad Alkasem
 *
 *
 * Implementation of the ContentService interface.
 * <p>
 * This class provides the concrete implementation of the ContentService interface.
 * It is marked with the @Service annotation, meaning that it is a service
 * component in the Spring Framework and can be automatically detected and
 * instantiated by Spring's component scanning.
 * <p>
 * It uses a ContentRepository to perform operations related to the contents.
 *
 * @see encoway.springframework.videoapplication.service.interfaces.ContentService
 */

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private final ContentRepository contentRepository;

    /**
     * constructs a new ContentServiceImpl object with the parameter
     * @param contentRepository to be used for the content`s operations
     */
    public ContentServiceImpl(ContentRepository contentRepository) {
        this.contentRepository = contentRepository;
    }

    /**
     * @method find the content in the database by its id
     * @param contentId is the ID of the content to be found
     * @return the content with the given id or null if it's not exists
     */
    @Override
    public Optional<Content> findById(Long contentId) {
        return contentRepository.findById(contentId);
    }

    /**
     * @method find set of contents in the database
     * @return set of contents or null
     */
    @Override
    public Set<Content> findAll() {
        Set<Content> contents = new HashSet<>();
        contentRepository.findAll().forEach(content -> contents.add(content));
        return contents;
    }

    /**
     * @method saves content to the database
     * @param content of the entity Content
     * @return the saved object content
     */
    @Override
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    /**
     * saves a set of contents to the database
     *
     * @param contents set of entities to be found
     * @return set of the saved_contents or null
     */
    @Override
    public Set<Content> saveAll(Set<Content> contents) {

        Set<Content> saved_contents = new HashSet<>();
        contentRepository.saveAll(contents).forEach(content -> saved_contents.add(content));
        return saved_contents;
    }

    /**
     * delete an content from the database
     *
     * @param content which would be deleted from the database
     */
    @Override
    public void delete(Content content) {
        contentRepository.delete(content);
    }

    /**
     * delete an object from the database by its id
     *
     * @param contentId of the object, which be deleted
     */
    @Override
    public void deleteById(Long contentId) {
        contentRepository.deleteById(contentId);

    }

    /**
     * delete a set of contents from the database
     *
     * @param contents that would be deleted from the database
     */
    @Override
    public void deleteAll(Set<Content> contents) {
        contentRepository.deleteAll(contents);

    }
}
