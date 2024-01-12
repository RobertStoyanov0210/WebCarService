package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.repository.BrandRepository;
import com.example.carservice.dto.brand.BrandDTO;
import com.example.carservice.dto.brand.CreateBrandDTO;
import com.example.carservice.dto.brand.UpdateBrandDTO;
import com.example.carservice.services.BrandService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.beans.ConstructorProperties;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<BrandDTO> getBrands() {
//        return brandRepository.findAll();
        return brandRepository.findAll().stream()
                .map(b -> modelMapper.map(b,BrandDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Brand create(CreateBrandDTO brand) {
        return brandRepository.save(modelMapper.map(brand, Brand.class));
    }

    @Override
    public BrandDTO getBrand(@Min(1) long id) {
        return modelMapper.map(brandRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Brand id is invalid!")),BrandDTO.class);
    }

    @Override
    public Brand updateBrand(long id, UpdateBrandDTO brandDTO) {
        Brand brand = modelMapper.map(brandDTO,Brand.class);
        brand.setId(id);
        return brandRepository.save(brand);
    }

    @Override
    public void deleteBrand(long id) {
        brandRepository.deleteById(id);
    }
}
