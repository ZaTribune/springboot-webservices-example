package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.Vendor;

public interface VendorRepository extends CrudRepository<Vendor,Long> {
    @Query(value = "select u from Vendor u where lower( u.title) like lower( CONCAT(?1,'%'))")
    Iterable<Vendor>findVendorsByName(String name);
}
