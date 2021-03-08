package zatribune.spring.example.webservices.data.dto;

import lombok.Data;
import zatribune.spring.example.webservices.data.entities.Category;

import java.util.Set;

@Data
public class ProductDTO {
    private Long id;
    private String name;
    private String url;
    private Set<Category> categories;
}
