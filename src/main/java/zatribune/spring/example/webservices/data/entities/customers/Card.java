package zatribune.spring.example.webservices.data.entities.customers;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String type;
    private String number;
    @OneToOne
    @JoinColumn(name = "expiration_date",referencedColumnName = "id")
    private ExpirationDate expirationDate;
}
