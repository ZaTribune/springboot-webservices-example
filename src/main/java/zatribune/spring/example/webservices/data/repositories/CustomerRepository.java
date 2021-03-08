package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.customers.Customer;

public interface CustomerRepository extends CrudRepository<Customer,Long> {
    @Query(value = "select u from Customer u where lower( u.name.title) like lower( CONCAT(?1,'%'))" +
            " or lower(u.name.first) like lower(CONCAT(?1,'%')) or lower(u.name.last) like lower(concat(?1,'%'))")
   Iterable<Customer>findUsersByName(String name);
}
