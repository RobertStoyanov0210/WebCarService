package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.CarServicePricelist;
import com.example.carservice.data.repository.CarServicePriceListRepository;
import com.example.carservice.data.repository.CarServiceRepository;
import com.example.carservice.dto.carServicePricelist.CreateCarServicePricelistDTO;
import com.example.carservice.services.CarServicePricelistService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarServicePricelistServiceImpl implements CarServicePricelistService {
    private final CarServicePriceListRepository carServicePriceListRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createPricelist(CreateCarServicePricelistDTO createCarServicePricelistDTO) {
        CarService carService = createCarServicePricelistDTO.getCarService();
        if (carService.getServiceQualifications().contains(createCarServicePricelistDTO.getQualification())) {
            carServicePriceListRepository.save(modelMapper.map(createCarServicePricelistDTO, CarServicePricelist.class));

        }
    }
}
