package zatribune.spring.example.webservices.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zatribune.spring.example.webservices.data.dto.ProductDTO;
import zatribune.spring.example.webservices.data.entities.Product;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE= Mappers.getMapper(ProductMapper.class);
    Product productDTOToProduct(ProductDTO productDTO);
    ProductDTO productToProductDTO(Product product);

}
