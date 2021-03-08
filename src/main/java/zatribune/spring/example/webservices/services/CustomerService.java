package zatribune.spring.example.webservices.services;

import zatribune.spring.example.webservices.data.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO getById(Long id);
    List<CustomerDTO> getCustomersByName(String name, Integer limit);
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO updateCustomer(Long id , CustomerDTO customerDTO);
    CustomerDTO patchCustomer(Long id , CustomerDTO customerDTO);
}
