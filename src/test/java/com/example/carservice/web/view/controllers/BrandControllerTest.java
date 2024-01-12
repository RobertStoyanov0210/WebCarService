package com.example.carservice.web.view.controllers;

import com.example.carservice.services.BrandService;
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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = BrandController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class BrandControllerTest {
    @MockBean
    private BrandService brandService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;

//    @WithMockUser(username = "admin",password = "admin")
//    @WithMockUser(roles = "ADMIN")
    @Test
    void getBrands() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/brands/brands"));
    }
    @Test
    void showBrandCreateForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/create-brand"))
                .andExpect(status().isOk())
                .andExpect(view().name("/brands/create-brand"));
    }
    @Test
    @Disabled
    void showBrandEditForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/brands/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/brands/edit-brand"));
    }
}
