package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.Product;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {

    @Query(value = "select u from Product u where lower( u.name) like lower( CONCAT(?1,'%'))")
    Iterable<Product>getAllByNameAbstracted(String name);
    @Query(value = "select u from Product u left join fetch u.categories where lower( u.name) like lower( CONCAT(?1,'%'))")
    Iterable<Product>getAllByNameJoined(String name);
    @Query(value = "select u from Product u left join fetch u.categories where u.id=?1")
    Optional<Product>findByIdJoined(Long id);
}
