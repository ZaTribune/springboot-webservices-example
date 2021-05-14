package zatribune.spring.example.webservices.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import zatribune.spring.example.webservices.data.dto.CategoryDTO;
import zatribune.spring.example.webservices.data.entities.Category;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);
    CategoryDTO toCategoryDTO(Category category);
    @Mapping(target = "products", ignore = true)
    Category toCategory(CategoryDTO categoryDTO);
}
