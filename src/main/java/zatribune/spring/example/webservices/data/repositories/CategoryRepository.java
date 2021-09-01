package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.Category;

import java.util.List;

public interface CategoryRepository extends CrudRepository<Category,Long> {
    @Query("select c from Category c where lower(c.name) like lower(concat(?1,'%'))")
    List<Category> findCategoriesByNameStartingWith(String name);
}
