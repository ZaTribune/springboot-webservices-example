package zatribune.spring.example.webservices.services;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.example.webservices.Exceptions.NotFoundException;
import zatribune.spring.example.webservices.data.dto.CustomerDTO;
import zatribune.spring.example.webservices.data.entities.customers.Customer;
import zatribune.spring.example.webservices.data.mappers.CustomerMapper;
import zatribune.spring.example.webservices.data.repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Api(tags ="Customers")
@RestController//@RestController=@Controller+@ResponseBody so there's no need to use @ResponseEntity, just use @HttpStatus
@RequestMapping("/v1/customers/")
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @ApiOperation(value = "To get a list of customers of size [limit] and name starting with [name]"
            ,notes = "defaults are name=\"\", limit=0")
    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> getCustomersByName(@RequestParam(required = false,defaultValue ="")String name,
                                                @RequestParam(required = false,defaultValue ="5") Integer limit) {
        return StreamSupport.stream(customerRepository.findUsersByName(name).spliterator(),false)
                .limit(limit)
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO getById(@PathVariable Long id) {
        return customerRepository.findById(id)
                .map(CustomerMapper.INSTANCE::customerToCustomerDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

/*PUT is for creating when you know the URL of the thing you will create.
POST can be used to create when you know the URL of the "factory" or manager
 for the category of things you want to create.*/
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO) {
        Customer customer=CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customerRepository.save(customer));
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    //default http status is 200 ok
    public CustomerDTO updateCustomer(@RequestParam Long id, @RequestBody CustomerDTO customerDTO){
        // we get a request with an id and a DTO
        //if the entity passed to the save() method has an id then it's an update
        customerDTO.setId(id);
        Customer customer=CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customerRepository.save(customer));
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO patchCustomer(@RequestParam Long id, @RequestBody CustomerDTO customerDTO){
        // we get a request with an id and a DTO
        //if the entity passed to the save() method has an id then it's an update
        customerDTO.setId(id);
        Customer customer=CustomerMapper.INSTANCE.customerDTOToCustomer(customerDTO);
        return CustomerMapper.INSTANCE.customerToCustomerDTO(customerRepository.save(customer));
    }


}
