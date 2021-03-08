package zatribune.spring.example.webservices.data.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class VendorDTO {
    @ApiModelProperty(required = true)
    private Long id;
    @ApiModelProperty(required = true,value = "Vendor's title or displayed name.")
    private String title;
    @ApiModelProperty(required = true,value = "Vendor's URL for the API.")
    private String url;
}
