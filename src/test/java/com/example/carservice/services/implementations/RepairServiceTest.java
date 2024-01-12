package com.example.carservice.services.implementations;


import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.repository.QualificationRepository;
import com.example.carservice.data.repository.RepairRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class RepairServiceTest {
    @MockBean
    private RepairRepository repairRepository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private RepairServiceImpl repairService;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    void getRepairs() {
        CarService carService = new CarService();
        carService.setId(1);
        carService.setName("cs");
        carService.setMaxRepairingCars(5);
        Repair repair = new Repair();
        repair.setId(3);
        repair.setCarService(carService);
//        repair.setName("b1");
        Repair repair1 = new Repair();
        repair1.setId(7);
        repair.setCarService(carService);
//        qualification1.setName("b2");

        Set<Repair> repairs = Set.of(repair1, repair);
//        List<BrandDTO> brands = List.of(brand,brand2).stream()
//                .map(b -> modelMapper.map(b,BrandDTO.class))
//                .collect(Collectors.toList());
        Mockito.when(repairRepository.findCurrentRepairsByCarService(anyInt()))
                .thenReturn(repairs);

//        brandRepository.save(brand);
//        brandRepository.save(brand2);

        assertThat(repairService.getCurrentRepairs(1))
                .hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(3L, 7L);
    }
}
