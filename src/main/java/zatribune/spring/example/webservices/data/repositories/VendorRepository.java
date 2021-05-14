package zatribune.spring.example.webservices.data.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import zatribune.spring.example.webservices.data.entities.Vendor;

import java.util.Optional;

public interface VendorRepository extends CrudRepository<Vendor,Long> {
    @Query(value = "select u from Vendor u where lower( u.title) like lower( CONCAT(?1,'%'))")
    Iterable<Vendor>findVendorsByName(String name);
    @Query(value = "select u from Vendor u left join fetch u.products p left join fetch p.categories where u.id = ?1 ")
    Optional<Vendor> findVendorByIdJoined(Long id);
}
