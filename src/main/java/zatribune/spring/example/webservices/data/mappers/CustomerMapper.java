package zatribune.spring.example.webservices.data.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zatribune.spring.example.webservices.data.entities.customers.Customer;
import zatribune.spring.example.webservices.data.dto.CustomerDTO;

@Mapper
public interface CustomerMapper {
    CustomerMapper INSTANCE= Mappers.getMapper(CustomerMapper.class);
    CustomerDTO toCustomerDTO(Customer customer);
    Customer toCustomer(CustomerDTO customerDTO);
}
