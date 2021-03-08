package zatribune.spring.example.webservices.services;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import zatribune.spring.example.webservices.Exceptions.NotFoundException;
import zatribune.spring.example.webservices.data.dto.VendorDTO;
import zatribune.spring.example.webservices.data.entities.Vendor;
import zatribune.spring.example.webservices.data.mappers.VendorMapper;
import zatribune.spring.example.webservices.data.repositories.VendorRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Slf4j
@Api(tags = "Vendors")
@RestController
@RequestMapping("/v1/vendors")
public class VendorServiceImpl implements VendorService{

    private final VendorRepository repository;

    @Autowired
    public VendorServiceImpl(VendorRepository repository) {
        this.repository=repository;
    }

    @ApiOperation("To get a list of vendors with size of [size] and name starting with [name].")
    @GetMapping("list")//@RequestMapping will make swagger generate all types of methods for this one
    @ResponseStatus(HttpStatus.OK)
    public List<VendorDTO> getVendorsByName(@RequestParam(required = false,defaultValue = "") String name,
                                            @RequestParam(required = false,defaultValue = "5") Integer limit) {
        return StreamSupport.stream(repository.findVendorsByName(name).spliterator(),false)
                .limit(limit)
                .map(VendorMapper.INSTANCE::vendorToVendorDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VendorDTO createVendor(@RequestBody VendorDTO v) {
        Vendor vendor=VendorMapper.INSTANCE.vendorDTOToVendor(v);
        return VendorMapper.INSTANCE.vendorToVendorDTO(repository.save(vendor));
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(VendorMapper.INSTANCE::vendorToVendorDTO)
                .orElseThrow(() -> new NotFoundException(id));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO updateVendor(@PathVariable Long id,@RequestBody VendorDTO v) {
        v.setId(id);
        Vendor vendor=VendorMapper.INSTANCE.vendorDTOToVendor(v);
        return VendorMapper.INSTANCE.vendorToVendorDTO(repository.save(vendor));
    }

    @ApiOperation("To update a specific property rather than specify the whole entity.")
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public VendorDTO patchVendor(@PathVariable Long id,@RequestBody VendorDTO v) {
        Vendor oldEntity=repository.findById(id).orElseThrow(() -> new NotFoundException(id));

        if (v.getTitle()!=null)
            oldEntity.setTitle(v.getTitle());
        if (v.getUrl()!=null)
            oldEntity.setUrl(v.getUrl());

        Vendor newEntity=repository.save(oldEntity);
        return VendorMapper.INSTANCE.vendorToVendorDTO(newEntity);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteVendor(@PathVariable Long id) {
        Vendor vendor=repository.findById(id).orElseThrow(()->new NotFoundException(id));
        repository.delete(vendor);
        return "Deleted!";
    }
}
