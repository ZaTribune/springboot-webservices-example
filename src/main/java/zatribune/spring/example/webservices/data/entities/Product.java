package zatribune.spring.example.webservices.data.entities;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Data
@Getter
@Setter
@EqualsAndHashCode(exclude = "categories")
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
    @ManyToMany
    @JoinTable(
            name = "vendor_product",
            joinColumns = @JoinColumn(name = "product"),
            inverseJoinColumns = @JoinColumn(name = "vendor"))
    private Set<Vendor> vendors;
    private String image;
}
