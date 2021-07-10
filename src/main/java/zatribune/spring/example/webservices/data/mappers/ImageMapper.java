package zatribune.spring.example.webservices.data.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

@Mapper
public interface ImageMapper {

    ImageMapper INSTANCE= Mappers.getMapper(ImageMapper.class);

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


    default byte[] toByteArrayUnWrapped(String image){
        byte[] result = new byte[0];
        try {
            BufferedImage bImage = ImageIO.read(new File(PropertiesExtractor.FILE_SERVER_PATH + image));
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write(bImage, "jpg", bos);
            result= bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
