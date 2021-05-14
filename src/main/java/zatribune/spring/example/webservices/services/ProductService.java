package zatribune.spring.example.webservices.services;

import zatribune.spring.example.webservices.data.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getById(Long id);
    List<ProductDTO> getProductsByName(String name, Integer limit);
    ProductDTO createProduct(ProductDTO p);
    ProductDTO updateProduct(Long id,ProductDTO p);
    ProductDTO patchProduct(Long id,ProductDTO p);
    String deleteProduct(Long id);
}
