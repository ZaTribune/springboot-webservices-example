package zatribune.spring.example.webservices.services;

import zatribune.spring.example.webservices.data.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    CategoryDTO getById(Long id);
    List<CategoryDTO> getCategoriesByName(String name, Integer limit);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO updateCategory(Long id,CategoryDTO categoryDTO);
    CategoryDTO patchCategory(Long id,CategoryDTO categoryDTO);
    String deleteCategory(Long id);
}
