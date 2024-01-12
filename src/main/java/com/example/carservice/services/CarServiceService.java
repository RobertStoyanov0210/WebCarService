package com.example.carservice.services;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.CarService;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.carService.CreateCarServiceDTO;
import com.example.carservice.dto.carService.UpdateCarServiceDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.web.view.model.employee.CreateEmployeeViewModel;

import javax.validation.constraints.Min;
import java.util.List;

public interface CarServiceService {
    void createCarService(CreateCarServiceDTO carServiceDTO);
    List<CarServiceDTO> getCarServices();
    List<CarServiceDTO> getCarServicesByBrand(Brand brand);
    CarServiceDTO getCarService(@Min(1) long id);
    void updateCarService(long id, UpdateCarServiceDTO carServiceDTO);
    void deleteCarService(long id);

    void updateCarServiceQualifications(CarService carService);

//    void updateAddToCarServiceEmployees(CarService carService, CreateEmployeeViewModel employee);
//    void updateRemoveFromCarServiceEmployees(CarService carService, EmployeeDTO employee);
}
