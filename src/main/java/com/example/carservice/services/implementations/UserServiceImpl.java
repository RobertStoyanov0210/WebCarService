package com.example.carservice.services.implementations;

import com.example.carservice.config.SecurityConfig;
import com.example.carservice.data.entity.Role;
import com.example.carservice.data.entity.User;
import com.example.carservice.data.repository.RoleRepository;
import com.example.carservice.data.repository.UserRepository;
import com.example.carservice.dto.carService.CarServiceDTO;
import com.example.carservice.dto.customer.CreateCustomerDTO;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.dto.employee.UpdateEmployeeDTO;
import com.example.carservice.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    @Override
    public void createEmployee(@Valid CreateEmployeeDTO employee) {
        employee.setPassword(new BCryptPasswordEncoder().encode(employee.getPassword()));
        Role employeeRole = roleRepository.findByAuthority("EMPLOYEE");
        employee.setAuthorities(Set.of(employeeRole));


        userRepository.save(modelMapper.map(employee, User.class));
    }

    @Override
    public void createCustomer(@Valid CreateCustomerDTO customer) {
        customer.setPassword(new BCryptPasswordEncoder().encode(customer.getPassword()));
        Role customerRole = roleRepository.findByAuthority("CUSTOMER");
        customer.setAuthorities(Set.of(customerRole));
        userRepository.save(modelMapper.map(customer, User.class));
    }

    @Override
    public List<EmployeeDTO> getEmployees() {
        Role employee = roleRepository.findByAuthority("EMPLOYEE");
        return userRepository.findAll().stream()
                .filter(user -> user.getAuthorities().contains(employee))
                .map(e -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployee(@Min(1) long id) {
        return modelMapper.map(userRepository.findById(id)
                        .orElseThrow(() -> new NullPointerException("Employee id invalid")), EmployeeDTO.class);
    }

    @Override
    public void updateEmployee(long id,@Valid UpdateEmployeeDTO updateEmployeeDTO) {
        User user = modelMapper.map(updateEmployeeDTO,User.class);
        Role customerRole = roleRepository.findByAuthority("EMPLOYEE");
        user.setId(id);
        user.setAuthorities(Set.of(customerRole));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));

        userRepository.save(user);
    }

    @Override
    public void deleteEmployee(long id) {
        if (userRepository.findById(id).isPresent())
            userRepository.deleteById(id);
    }
}
