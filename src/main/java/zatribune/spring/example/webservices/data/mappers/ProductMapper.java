package zatribune.spring.example.webservices.data.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import zatribune.spring.example.webservices.data.dto.CategoryDTO;
import zatribune.spring.example.webservices.data.dto.ProductDTO;
import zatribune.spring.example.webservices.data.entities.Category;
import zatribune.spring.example.webservices.data.entities.Product;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "vendors", ignore = true)
    //qualifiedByName = "image" will link the target
    //to the specified @Named mapping method
    @Mapping(target = "image", qualifiedByName = "image")
    Product toProduct(ProductDTO productDTO);

    @Mapping(target = "categories", qualifiedByName = "category")
    @Mapping(target = "image", qualifiedByName = "image")
    ProductDTO toProductDTO(Product product);

    @Named("category")
    default CategoryDTO map(Category category) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(category.getName());
        categoryDTO.setUrl(category.getUrl());
        categoryDTO.setId(category.getId());
        return categoryDTO;
    }


    @Named("image")
    default String map(Byte[] product) {
        //todo
        return "";
    }

    @Named("image")
    default Byte[] map(String product) {
//        Byte[]temp = new Byte[0];
//        File file = new File(PropertiesExtractor.FILE_SERVER_PATH + product);
//        try {
//            byte[] bytes = Files.readAllBytes(file.toPath());
//            temp=new Byte[bytes.length];
//            int i = 0;
//            for (byte b : bytes)
//                temp[i++] = b;
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return temp;
        Byte[] temp = new Byte[0];
        try {
            BufferedImage bImage = ImageIO.read(new File(PropertiesExtractor.FILE_SERVER_PATH + product));

            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            byte[] data = bos.toByteArray();
            temp = new Byte[data.length];
            int i = 0;
            for (byte b : data)
                temp[i++] = b;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return temp;
    }
}