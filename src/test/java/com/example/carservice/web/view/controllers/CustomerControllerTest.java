package com.example.carservice.web.view.controllers;


import com.example.carservice.services.*;
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
@WebMvcTest(value = CustomerController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {
    @MockBean
    private UserService userService;
    @MockBean
    private ReservationService reservationService;
    @MockBean
    private RepairService repairService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void showCustomerCreateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/create-customer"));
    }
    @Test
    @Disabled
    void getCustomerVehicles() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/reservations/my"))
                .andExpect(status().isOk())
                .andExpect(view().name("/customers/customer-reservations"));
    }
}
