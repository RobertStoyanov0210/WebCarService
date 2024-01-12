package com.example.carservice.web.view.controllers;


import com.example.carservice.services.BrandService;
import com.example.carservice.services.QualificationService;
import com.example.carservice.services.VehicleService;
import org.junit.jupiter.api.Disabled;
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
@WebMvcTest(value = VehicleController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class VehicleControllerTest {
    @MockBean
    private VehicleService vehicleService;
    @MockBean
    private BrandService brandService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    @Disabled
    void getCustomerVehicles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("/vehicles/my-vehicles"));
    }
    @Test
    void showVehicleCreateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/create-vehicle"))
                .andExpect(status().isOk())
                .andExpect(view().name("/vehicles/create-vehicle"));
    }
    @Test
    @Disabled
    void showVehicleEditForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/vehicles/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/qualifications/edit-qualification"));
    }
}
