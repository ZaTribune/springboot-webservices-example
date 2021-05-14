package zatribune.spring.example.webservices.data.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Set;

@Getter
@Setter
public class ProductDTO {
    private Long id;
    private String name;
    private String url;
    private Set<CategoryDTO> categories;
    private Byte[]image;
}
