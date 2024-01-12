package com.example.carservice.web.view.controllers;

import com.example.carservice.services.CarServiceService;
import com.example.carservice.services.QualificationService;
import com.example.carservice.services.UserService;
import com.example.carservice.services.VehicleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = EmployeeController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private QualificationService qualificationService;
    @MockBean
    private CarServiceService carServiceService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getEmployees() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/employees/employees"));
    }
    @Test
    void showEmployeeCreateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/employees/create-employee"))
                .andExpect(status().isOk())
                .andExpect(view().name("/employees/create-employee"));
    }
}
