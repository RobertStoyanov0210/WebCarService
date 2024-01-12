package com.example.carservice.web.view.controllers;

import com.example.carservice.services.BrandService;
import com.example.carservice.services.QualificationService;
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
@WebMvcTest(value = QualificationController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class,
        excludeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = WebSecurityConfigurer.class))
@AutoConfigureMockMvc(addFilters = false)
public class QualificationControllerTest {
    @MockBean
    private QualificationService qualificationService;
    @MockBean
    private ModelMapper modelMapper;
    @Autowired
    private MockMvc mockMvc;


    @Test
    void getQualifications() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/qualifications/"))
                .andExpect(status().isOk())
                .andExpect(view().name("/qualifications/qualifications"));
    }
    @Test
    void createQualification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/qualifications/create-qualification"))
                .andExpect(status().isOk())
                .andExpect(view().name("/qualifications/create-qualification"));
    }
    @Test
    @Disabled
    void showQualificationEditForm() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/qualifications/edit/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("/qualifications/edit-qualification"));
    }

}
