package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.repository.CarServiceRepository;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.dto.repair.RepairDTO;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.web.view.model.employee.CreateEmployeeViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class CarServiceServiceImpl implements CarServiceService {
    private final CarServiceRepository carServiceRepository;
    private final ModelMapper modelMapper;

    @Override
    public void createCarService(CreateCarServiceDTO carServiceDTO) {
        carServiceRepository.save(modelMapper.map(carServiceDTO, CarService.class));
    }

    @Override
    public List<CarServiceDTO> getCarServices() {
        return carServiceRepository.findAll().stream()
                .map(cs -> modelMapper.map(cs,CarServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarServiceDTO> getCarServicesByBrand(Brand brand) {
        return carServiceRepository.getCarServicesBySupportedBrandsContains(brand).stream()
                .map(cs -> modelMapper.map(cs,CarServiceDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public CarServiceDTO getCarService(@Min(1) long id) {
        return modelMapper.map(carServiceRepository.findById(id).orElseThrow(
                () -> new NullPointerException("Car service id is invalid!")),CarServiceDTO.class);
    }

    @Override
    public void updateCarService(long id, UpdateCarServiceDTO carServiceDTO) {
        CarService carService = modelMapper.map(carServiceDTO,CarService.class);
        carService.setId(id);
        carServiceRepository.save(carService);
    }

    @Override
    public void deleteCarService(long id) {
        carServiceRepository.deleteById(id);
    }

    @Override
    public void updateCarServiceQualifications(CarService carService) {
        Set<EmployeeDTO> employeesDTO = carService.getServiceEmployees().stream()
                .map(u -> modelMapper.map(u,EmployeeDTO.class))
                .collect(Collectors.toSet());
        Set<Qualification> updated = new HashSet<>();

        for(EmployeeDTO emp : employeesDTO){
            updated.addAll(emp.getQualifications());
        }
        carService.setServiceQualifications(updated);
        carServiceRepository.save(carService);
    }

//    @Override
//    public void updateAddToCarServiceEmployees(CarService carService, CreateEmployeeViewModel employee) {
////        carService.getServiceEmployees().addAll(Set.of(modelMapper.map(employee, User.class)));
//        carService.getServiceEmployees().add(modelMapper.map(employee,User.class));
//        carServiceRepository.save(carService);
//        updateCarServiceQualifications(carService);
//    }
//
//    @Override
//    public void updateRemoveFromCarServiceEmployees(CarService carService, EmployeeDTO employee) {
//        carService.getServiceEmployees().remove(modelMapper.map(employee,User.class));
//        carServiceRepository.save(carService);
//        updateCarServiceQualifications(carService);
//    }


}
