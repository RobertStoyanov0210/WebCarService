package com.example.carservice.web.view.controllers;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.dto.employee.CreateEmployeeDTO;
import com.example.carservice.dto.employee.EmployeeDTO;
import com.example.carservice.dto.employee.UpdateEmployeeDTO;
//import com.example.carservice.services.EmployeeService;
import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.QualificationService;
import com.example.carservice.services.RoleService;
import com.example.carservice.services.UserService;
import com.example.carservice.web.view.model.carService.CarServiceViewModel;
import com.example.carservice.web.view.model.employee.CreateEmployeeViewModel;
import com.example.carservice.web.view.model.employee.EmployeeViewModel;
import com.example.carservice.web.view.model.employee.UpdateEmployeeViewModel;
import com.example.carservice.web.view.model.qualification.QualificationViewModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
@RequestMapping("/employees")
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class EmployeeController {

    private final UserService userService;
    private final QualificationService qualificationService;
    private final CarServiceService carServiceService;
    //private final RoleService roleService;
    private final ModelMapper modelMapper;

    @GetMapping("/")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getEmployees(Model model) {
        model.addAttribute("employees", userService.getEmployees().stream()
                .map(e -> modelMapper.map(e, EmployeeViewModel.class))
                .collect(Collectors.toList()));
        return "/employees/employees";
    }

    @GetMapping("/create-employee")
    public String showEmployeeCreateForm(Model model) {
        model.addAttribute("employee", new CreateEmployeeViewModel());
        model.addAttribute("qualificationsALL", qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("carServices",carServiceService.getCarServices().stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
//        model.addAttribute("roless",roleService.getRoles());

        return "/employees/create-employee";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute("employee") CreateEmployeeViewModel employee, BindingResult bindingResult) {
//        employee.setUsername(employee.getFirstName() + employee.getLastName());
        if (bindingResult.hasErrors()) {
            return "/employees/create-employee";
        }
//        System.out.println("pass(in controller)");
        userService.createEmployee(modelMapper.map(employee, CreateEmployeeDTO.class));

        carServiceService.updateCarServiceQualifications(employee.getCarService());
//        carServiceService.updateAddToCarServiceEmployees(employee.getCarService(),employee);
        return "redirect:/employees/";
    }
    @GetMapping("/edit/{id}")
    public String showEmployeeEditForm(Model model,@PathVariable long id){
        System.out.println("DEBUGGING");
        UpdateEmployeeViewModel employee = modelMapper.map(userService.getEmployee(id),UpdateEmployeeViewModel.class);
//        System.out.println(employee);
//        System.out.println(employee.getQualifications());
//        System.out.println(qualificationService.getQualifications().stream()
//                .map(q -> modelMapper.map(q, QualificationViewModel.class))
//                .collect(Collectors.toList()));
        model.addAttribute("employee",employee);
        model.addAttribute("qualificationsALL", qualificationService.getQualifications().stream()
                .map(q -> modelMapper.map(q, QualificationViewModel.class))
                .collect(Collectors.toList()));
        model.addAttribute("carServices",carServiceService.getCarServices().stream()
                .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                .collect(Collectors.toList()));
        return "/employees/edit-employee";
    }
    //TODO fix it(selected qualifications not loading)
    @PostMapping("/update/{id}")
    public String updateEmployee(@PathVariable long id,@Valid @ModelAttribute("employee") UpdateEmployeeViewModel employee,BindingResult bindingResult,Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("qualificationsALL", qualificationService.getQualifications().stream()
                    .map(q -> modelMapper.map(q, QualificationViewModel.class))
                    .collect(Collectors.toList()));
            model.addAttribute("carServices",carServiceService.getCarServices().stream()
                    .map(cs -> modelMapper.map(cs, CarServiceViewModel.class))
                    .collect(Collectors.toList()));
            return "/employees/edit-employee";
        }
        userService.updateEmployee(id,modelMapper.map(employee, UpdateEmployeeDTO.class));
        CarService carService = userService.getEmployee(id).getCarService();
        carServiceService.updateCarServiceQualifications(carService);

        return "redirect:/employees/";
    }
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable long id){
        CarService carService = userService.getEmployee(id).getCarService();
//        carServiceService.updateRemoveFromCarServiceEmployees(employeeDTO.getCarService(),employeeDTO);
        userService.deleteEmployee(id);
        carServiceService.updateCarServiceQualifications(carService);
        return "redirect:/employees/";
    }

    //Get all vehicles repaired in given car service

    //View all current repair and be able to finish them

}
