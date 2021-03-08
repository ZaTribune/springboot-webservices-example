package zatribune.spring.example.webservices.data.entities;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String url;
    @ManyToMany
    @JoinTable(
            name = "category_product",
            joinColumns = @JoinColumn(name = "product"),
            inverseJoinColumns = @JoinColumn(name = "category"))
    private Set<Category> categories;
}
