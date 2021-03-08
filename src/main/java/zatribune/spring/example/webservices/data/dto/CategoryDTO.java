package zatribune.spring.example.webservices.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CategoryDTO {
    @ApiModelProperty(required = true)
    private Long id;
    @ApiModelProperty(value = "category's name",required = true)
    private String name;
    @ApiModelProperty(value = "category's URL",required = true)
    private String url;
}
