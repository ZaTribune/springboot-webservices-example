package zatribune.spring.example.webservices.data.entities.customers;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "name" ,referencedColumnName = "id")
    private Name name;
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location" ,referencedColumnName = "id")
    private Location location;
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "login" ,referencedColumnName = "id")
    private Login login;
    private String phone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job" ,referencedColumnName = "id")
    private Job job;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "billing" ,referencedColumnName = "id")
    private Billing billing;
    private String language;
    private String currency;
    private String url;


    @Override
    public String toString() {
        return name+"\t"+email;
    }
}
