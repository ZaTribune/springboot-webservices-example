package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.customers.Name;

public interface NameRepository extends CrudRepository<Name,Long> {
    @Query(value = "select u from Name u where u.title like CONCAT('%',?1)" +
            " or u.first like CONCAT('%',?1) or u.last like CONCAT('%',?1)")
    Iterable<Name>findNamesByString(String name);
}
