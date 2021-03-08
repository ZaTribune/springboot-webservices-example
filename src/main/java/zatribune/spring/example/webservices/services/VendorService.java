package zatribune.spring.example.webservices.services;

import zatribune.spring.example.webservices.data.dto.VendorDTO;

import java.util.List;

public interface VendorService {
    VendorDTO getById(Long id);
    List<VendorDTO> getVendorsByName(String name, Integer limit);
    VendorDTO createVendor(VendorDTO v);
    VendorDTO updateVendor(Long id,VendorDTO v);
    VendorDTO patchVendor(Long id,VendorDTO v);
    String deleteVendor(Long id);
}
