package zatribune.spring.example.webservices.data.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @ManyToMany(mappedBy = "categories")
    private Set<Product> products;
}
