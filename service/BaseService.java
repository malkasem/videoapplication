package encoway.springframework.videoapplication.service;

import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;

/**
 * it represents base Dao and defines an abstract API that performs CRUD operations of CrudRepository
 * @author Mohamad Alkasem
 * @version 1.0
 *
 *
 *
 * @param <T> refers to the domain datatype/entity
 * @param <ID> refers to the datatype of the @Id
 */
public interface BaseService <T, ID>{

    /**
     * @method find the object in the database by its id
     * @param id is the ID of the object to be found
     * @return the object with the given id or null if it's not exists
     */
   Optional<T> findById(ID id);
    /**
     * @method find set of objects in the database
     * @return set of objects or null
     */
    Set<T> findAll();

    /**
     * @method saves object to the database
     * @param object of the entity
     * @return the saved object
     */
   T save(T object);

    /**
     * saves a set of objects to teh database
     * @param objects set of entities to be found
     * @return set of the objects or null
     */
    Set<T> saveAll(Set<T> objects);

    /**
     * delete an object from the database
     * @param object which would be deleted from the database
     */
    void delete(T object);

    /**
     * delete an object from the database by its id
     * @param id of the object, which be deleted
     */
    void deleteById(ID id);

    /**
     * delete a set of objects from the database
     * @param objects that would be deleted from the database
     */
    void deleteAll(Set<T> objects);
}
