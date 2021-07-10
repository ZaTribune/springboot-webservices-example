package zatribune.spring.example.webservices.data.dto;

import lombok.Getter;
import lombok.Setter;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@Getter
@Setter
@XmlRootElement
public class ProductDTO {
    private Long id;
    private String name;
    private String url;
    private Set<CategoryDTO> categories;
}
