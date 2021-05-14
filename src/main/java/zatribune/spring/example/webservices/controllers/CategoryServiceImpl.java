package zatribune.spring.example.webservices.controllers;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.example.webservices.Exceptions.NotFoundException;
import zatribune.spring.example.webservices.data.dto.CategoryDTO;
import zatribune.spring.example.webservices.data.mappers.CategoryMapper;
import zatribune.spring.example.webservices.data.entities.Category;
import zatribune.spring.example.webservices.data.repositories.CategoryRepository;
import zatribune.spring.example.webservices.services.CategoryService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Api(tags = "Categories")
@RestController
@RequestMapping("/v1/categories/")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository repository) {
        this.repository = repository;
    }

    @GetMapping("list")
    @ResponseStatus(HttpStatus.OK)
    public List<CategoryDTO> getCategoriesByName(@RequestParam(defaultValue = "",required = false) String name,
                                                 @RequestParam(defaultValue = "5",required = false) Integer limit) {
        return StreamSupport.stream(repository.findCategoriesByNameStartingWith(name).spliterator(),false)
                .limit(limit)
                .map(CategoryMapper.INSTANCE::toCategoryDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryDTO createCategory(@RequestBody CategoryDTO categoryDTO) {
        Category category=CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category=repository.save(category);
        return CategoryMapper.INSTANCE.toCategoryDTO(category);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getById(@PathVariable Long id) throws NotFoundException {
        return repository.findById(id)
                .map(CategoryMapper.INSTANCE::toCategoryDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO updateCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO) {
        //todo:: needs rethinking
        categoryDTO.setId(id);
        Category category=CategoryMapper.INSTANCE.toCategory(categoryDTO);
        category=repository.save(category);
        return CategoryMapper.INSTANCE.toCategoryDTO(category);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO patchCategory(@PathVariable Long id,@RequestBody CategoryDTO categoryDTO) {
        Category oldEntity=repository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (categoryDTO.getName()!=null)
        oldEntity.setName(categoryDTO.getName());
        if (categoryDTO.getUrl()!=null)
        oldEntity.setUrl(categoryDTO.getUrl());

        Category newEntity=repository.save(oldEntity);
        return CategoryMapper.INSTANCE.toCategoryDTO(newEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteCategory(@PathVariable Long id) {
        Category category=repository.findById(id).orElseThrow(()->new NotFoundException(id));
        repository.delete(category);
        return "Deleted!";
    }
}
