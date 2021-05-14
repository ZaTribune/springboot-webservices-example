package zatribune.spring.example.webservices.data.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import zatribune.spring.example.webservices.data.dto.VendorDTO;
import zatribune.spring.example.webservices.data.entities.Vendor;

@Mapper
public interface VendorMapper {
    VendorMapper INSTANCE= Mappers.getMapper(VendorMapper.class);
    VendorDTO toVendorDTO(Vendor vendor);
    Vendor toVendor(VendorDTO vendorDTO);
}
