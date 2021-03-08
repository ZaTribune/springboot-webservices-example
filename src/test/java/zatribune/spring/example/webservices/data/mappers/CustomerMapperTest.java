package zatribune.spring.example.webservices.data.mappers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zatribune.spring.example.webservices.data.dto.CustomerDTO;
import zatribune.spring.example.webservices.data.entities.customers.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMapperTest {

    CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    @BeforeEach
    void setUp() {
    }

    @Test
    void userToUserDTO() {
        Customer customer =new Customer();
        Name name=new Name();
        name.setTitle("Mr.");
        name.setFirst("Muhammad");
        name.setLast("Ali");
        Location location=new Location();
        location.setCity("Cairo");
        Job job=new Job();
        job.setTitle("AssKicker");
        customer.setName(name);
        customer.setLocation(location);


        customer.setEmail("whatever10215@gmail.com");
        customer.setGender(Gender.male);

        CustomerDTO customerDTO = customerMapper.customerToCustomerDTO(customer);

        assertEquals(customerDTO.getName(), customer.getName());
        assertEquals(customerDTO.getEmail(), customer.getEmail());
        assertEquals(customerDTO.getGender(), customer.getGender());
        assertEquals(customerDTO.getLocation(), customer.getLocation());
        assertEquals(customerDTO.getJob(), customer.getJob());
    }
}