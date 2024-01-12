package com.example.carservice.services.implementations;

//import com.example.carservice.data.entity.Employee;
//import com.example.carservice.data.repository.EmployeeRepository;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.dto.employee.UpdateEmployeeDTO;
//import com.example.carservice.services.EmployeeService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

//@Service
//@AllArgsConstructor
//@Validated
//public class EmployeeServiceImpl implements EmployeeService {
//    private final EmployeeRepository employeeRepository;
//    private final ModelMapper modelMapper;
//
//    @Override
//    public EmployeeDTO getEmployee(@Min(1) long id) {
//        return modelMapper.map(employeeRepository.findById(id)
//                .orElseThrow(() -> new NullPointerException("Employee id invalid")), EmployeeDTO.class);
//    }
//
//    @Override
//    public List<EmployeeDTO> getEmployees() {
//        return employeeRepository.findAll().stream()
//                .map(e -> modelMapper.map(e, EmployeeDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public void create(@Valid CreateEmployeeDTO employee) {
//        employeeRepository.save(modelMapper.map(employee, Employee.class));
//    }
//
//    @Override
//    public Employee updateEmployee(long id, UpdateEmployeeDTO employeeDTO) {
//        Employee employee = modelMapper.map(employeeDTO, Employee.class);
//        employee.setId(id);
//        return employeeRepository.save(employee);
//    }
//
//    @Override
//    public void deleteEmployee(long id) {
//        employeeRepository.deleteById(id);
//    }
//}
