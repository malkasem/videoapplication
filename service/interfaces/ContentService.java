package encoway.springframework.videoapplication.service.interfaces;

import encoway.springframework.videoapplication.model.Content;
import encoway.springframework.videoapplication.service.BaseService;

/**
 * Service interface for managing contents.
 * This interface extends the BaseService interface with Content as the entity type and Long as the ID type.
 *
 * @author Mohamad Alkasem
 *
 * @see encoway.springframework.videoapplication.service.BaseService
 */
public interface ContentService extends BaseService<Content, Long> {
}
