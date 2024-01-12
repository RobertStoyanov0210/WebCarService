package com.example.carservice.services;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.dto.brand.BrandDTO;
import com.example.carservice.dto.brand.CreateBrandDTO;
import com.example.carservice.dto.brand.UpdateBrandDTO;

import javax.validation.constraints.Min;
import java.util.List;

public interface BrandService{
    List<BrandDTO> getBrands();
    Brand create(CreateBrandDTO brand);
    BrandDTO getBrand(@Min(1) long id);
    Brand updateBrand(long id, UpdateBrandDTO brandDTO);
    void deleteBrand(long id);

}
