package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.repository.BrandRepository;
import com.example.carservice.dto.brand.BrandDTO;
import com.example.carservice.dto.brand.CreateBrandDTO;
import com.example.carservice.dto.brand.UpdateBrandDTO;
import com.example.carservice.services.BrandService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
    @MockBean
    private BrandRepository brandRepository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    void getBrands() {
        Brand brand = new Brand();
        brand.setId(3);
        brand.setName("b1");
        Brand brand2 = new Brand();
        brand2.setId(7);
        brand2.setName("b2");

        List<Brand> brands = List.of(brand, brand2);
//        List<BrandDTO> brands = List.of(brand,brand2).stream()
//                .map(b -> modelMapper.map(b,BrandDTO.class))
//                .collect(Collectors.toList());
        Mockito.when(brandRepository.findAll())
                .thenReturn(brands);

//        brandRepository.save(brand);
//        brandRepository.save(brand2);

        assertThat(brandService.getBrands())
                .hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(3L, 7L);
    }

    @Test
    void create() {
        Brand brand = new Brand();
        brand.setId(3);
        brand.setName("br");

        Mockito.when(brandRepository.save(any()))
                .thenReturn(brand);
//        Mockito.when(brandRepository.findById(brand.getId()))
//                .thenReturn(Optional.of(brand));

//        brandService.create(modelMapper.map(brand,CreateBrandDTO.class));
        System.out.println(modelMapper.map(brand, CreateBrandDTO.class));
        assertThat(brandService.create(modelMapper.map(brand, CreateBrandDTO.class)).getId()).isEqualTo(3);
    }

    //
    @Test
    void getBrand() {
        Brand brand = new Brand();
        brand.setName("brand");
        brand.setId(2);
        Mockito.when(brandRepository.findById(brand.getId())).thenReturn(Optional.of(brand));

        assertThat(brandService.getBrand(brand.getId()).getId()).isEqualTo(2);
    }

    @Test
    void updateBrand() {
        Brand old = new Brand();
        old.setName("brandOld");
        Brand updateBrandDTO = new Brand();
        updateBrandDTO.setId(7);
        updateBrandDTO.setName("brandEdit");

        Mockito.when(brandRepository.save(any())).thenReturn(old);

        assertThat(brandService.updateBrand(old.getId(), modelMapper.map(updateBrandDTO, UpdateBrandDTO.class)).getId()).isEqualTo(old.getId());

    }

    @Test
    void deleteBrand() {
        Brand brand = new Brand();
        brand.setId(11);
        brand.setName("bra");

        Mockito.when(brandRepository.findById(any())).thenReturn(Optional.of(brand));

        assertThat(brandService.getBrand(11).getName()).isEqualTo("bra");
        brandService.deleteBrand(11);
        assertThat(brandService.getBrands()).hasSize(0);
    }
}
