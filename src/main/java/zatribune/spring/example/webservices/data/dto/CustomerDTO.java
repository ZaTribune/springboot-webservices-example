package zatribune.spring.example.webservices.data.dto;

import lombok.Data;
import zatribune.spring.example.webservices.data.entities.customers.*;

@Data
public class CustomerDTO {
    private Long id;
    private Name name;
    private Gender gender;
    private Location location;
    private String email;
    private Login login;
    private String phone;
    private Job job;
    private Billing billing;
    private String language;
    private String currency;
    private String url;
}
