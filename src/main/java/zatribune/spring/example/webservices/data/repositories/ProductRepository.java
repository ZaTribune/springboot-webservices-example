package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.Product;

public interface ProductRepository extends CrudRepository<Product,Long> {

}
