package zatribune.spring.example.webservices.data.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import zatribune.spring.example.webservices.data.entities.customers.Name;
import zatribune.spring.example.webservices.data.entities.customers.Customer;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    NameRepository nameRepository;

    Customer u1,u2,u3;
    Name n1,n2,n3;

    @BeforeEach
    void setUp() {
        n1=new Name();
        n1.setTitle("Mr");
        n1.setFirst("Muhammad");
        n1.setLast("Ali");

        n2=new Name();
        n2.setFirst("Sarah");
        n2.setLast("Smith");

        n3=new Name();
        n3.setTitle("Mrs");
        n3.setFirst("Aron");
        n3.setLast("Whatever");

        u1=new Customer();
        u1.setEmail("muhammadali4544@gmail.com");
        u1.setName(n1);
        u2=new Customer();
        u2.setEmail("sarahsmith9878@yahoo.com");
        u2.setName(n2);
        u3=new Customer();
        u3.setEmail("aronwhatever675@outlook.com");
        u3.setName(n3);

    }

    @Test
    void findCustomersByName() {
        List<Customer> customers = StreamSupport.stream(customerRepository.saveAll(List.of(u1,u2,u3)).spliterator(),false)
                .collect(Collectors.toList());
        assertEquals(3, customers.size());
        assertEquals(3, StreamSupport.stream(nameRepository.findAll().spliterator(), false).count());

        List<Customer>result=StreamSupport.stream(customerRepository.findUsersByName("muhammad").spliterator(),false)
                .collect(Collectors.toList());
        System.out.println(result);
        assertEquals(1,result.size());
    }
}