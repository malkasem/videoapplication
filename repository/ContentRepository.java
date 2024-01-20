package encoway.springframework.videoapplication.repository;

import encoway.springframework.videoapplication.model.Content;
import org.springframework.data.repository.CrudRepository;

/**
 * represents the Content persistence, which will act as database repository for the contents
 * @autor Mohamad Alkasem
 * @version 1.0
 */
public interface ContentRepository extends CrudRepository<Content, Long> {
}
