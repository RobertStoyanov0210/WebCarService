package com.example.carservice.services;

import com.example.carservice.data.entity.User;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.dto.employee.UpdateEmployeeDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

public interface UserService extends UserDetailsService {
    void createEmployee(CreateEmployeeDTO employee);
    void createCustomer(CreateCustomerDTO customer);
    List<EmployeeDTO> getEmployees();
    EmployeeDTO getEmployee(@Min(1) long id);
    void updateEmployee(@Min(1) long id,@Valid UpdateEmployeeDTO updateEmployeeDTO);
    void deleteEmployee(long id);

}
