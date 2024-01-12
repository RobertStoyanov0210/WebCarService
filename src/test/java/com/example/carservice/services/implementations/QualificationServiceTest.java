package com.example.carservice.services.implementations;


import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.repository.BrandRepository;
import com.example.carservice.data.repository.QualificationRepository;
import com.example.carservice.dto.brand.CreateBrandDTO;
import com.example.carservice.dto.brand.UpdateBrandDTO;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class QualificationServiceTest {
    @MockBean
    private QualificationRepository qualificationRepository;
    @Mock
    private ModelMapper modelMapper;
    @Autowired
    private QualificationServiceImpl qualificationService;

    @BeforeEach
    public void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    void getQualifications() {
        Qualification qualification = new Qualification();
        qualification.setId(3);
        qualification.setName("b1");
        Qualification qualification1 = new Qualification();
        qualification1.setId(7);
        qualification1.setName("b2");

        List<Qualification> qualifications = List.of(qualification, qualification1);
//        List<BrandDTO> brands = List.of(brand,brand2).stream()
//                .map(b -> modelMapper.map(b,BrandDTO.class))
//                .collect(Collectors.toList());
        Mockito.when(qualificationRepository.findAll())
                .thenReturn(qualifications);

//        brandRepository.save(brand);
//        brandRepository.save(brand2);

        assertThat(qualificationService.getQualifications())
                .hasSize(2)
                .extracting("id")
                .containsExactlyInAnyOrder(3L, 7L);
    }

    @Test
    void create() {
        Qualification qualification = new Qualification();
        qualification.setId(3);
        qualification.setName("br");

        Mockito.when(qualificationRepository.save(any()))
                .thenReturn(qualification);
        System.out.println(modelMapper.map(qualification, CreateQualificationDTO.class));
        assertThat(qualificationService.create(modelMapper.map(qualification, CreateQualificationDTO.class)).getId()).isEqualTo(3);
    }

    //
    @Test
    void getQualification() {
        Qualification qualification = new Qualification();
        qualification.setName("brand");
        qualification.setId(2);
        Mockito.when(qualificationRepository.findById(qualification.getId())).thenReturn(Optional.of(qualification));

        assertThat(qualificationService.getQualification(qualification.getId()).getId()).isEqualTo(2);
    }

    @Test
    void updateQualification() {
        Qualification old = new Qualification();
        old.setName("qualificationOld");
        Qualification updateQualificationDTO = new Qualification();
        updateQualificationDTO.setId(7);
        updateQualificationDTO.setName("qualificationEdit");

        Mockito.when(qualificationRepository.save(any())).thenReturn(old);

        assertThat(qualificationService.updateQualification(old.getId(), modelMapper.map(updateQualificationDTO, UpdateQualificationDTO.class)).getId()).isEqualTo(old.getId());

    }

    @Test
    void deleteQualification() {
        Qualification qualification = new Qualification();
        qualification.setId(11);
        qualification.setName("bra");

        Mockito.when(qualificationRepository.findById(any())).thenReturn(Optional.of(qualification));

        assertThat(qualificationService.getQualification(11).getName()).isEqualTo("bra");
        qualificationService.deleteQualification(11);
        assertThat(qualificationService.getQualifications()).hasSize(0);
    }
}
