package zatribune.spring.example.webservices.controllers;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.example.webservices.Exceptions.NotFoundException;
import zatribune.spring.example.webservices.data.dto.ProductDTO;
import zatribune.spring.example.webservices.data.entities.Product;
import zatribune.spring.example.webservices.data.mappers.ImageMapper;
import zatribune.spring.example.webservices.data.mappers.ProductMapper;
import zatribune.spring.example.webservices.data.repositories.ProductRepository;
import zatribune.spring.example.webservices.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Api(tags = "Products")
@RequestMapping("/v1/products")
@RestController
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ProductMapper.INSTANCE::toProductDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

    //consumes->Content-Type header
    //produces->Accept header
    @ApiOperation(value = "To display a product image using its id",produces = "image/jpeg")
    @GetMapping(value = "/image",produces =MediaType.IMAGE_JPEG_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public byte[] getImage(@RequestParam("id") Long id) {
        String image = repository.findById(id)
                .orElseThrow(() -> new NotFoundException(id))
                .getImage();
        return ImageMapper.INSTANCE.toByteArrayUnWrapped(image);
    }

    @ApiOperation(value = "Returns a list of products.",produces ="application/json, application/xml" )
    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDTO> getProductsByName(@RequestParam(required = false, defaultValue = "") String name
            , @RequestParam(required = false, defaultValue = "5") Integer limit) {
        return StreamSupport.stream(repository.getAllByNameJoined(name).spliterator(), false)
                .limit(limit)
                .map(ProductMapper.INSTANCE::toProductDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO createProduct(@RequestBody ProductDTO p) {
        Product product = ProductMapper.INSTANCE.toProduct(p);
        return ProductMapper.INSTANCE.toProductDTO(repository.save(product));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updateProduct(@PathVariable Long id, @RequestBody ProductDTO p) {
        repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        p.setId(id);
        Product product = ProductMapper.INSTANCE.toProduct(p);
        return ProductMapper.INSTANCE.toProductDTO(repository.save(product));
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO patchProduct(@PathVariable Long id, @RequestBody ProductDTO p) {
        Product oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (p.getName() != null)
            oldEntity.setName(p.getName());
        if (p.getUrl() != null)
            oldEntity.setUrl(p.getUrl());

        Product newEntity = repository.save(oldEntity);
        return ProductMapper.INSTANCE.toProductDTO(newEntity);

    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        Product oldEntity = repository.findById(id).orElseThrow(() -> new NotFoundException(id));
        repository.delete(oldEntity);
        return "Deleted!";
    }

}
