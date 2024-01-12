package com.example.carservice.services;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.List;

public interface QualificationService {
    QualificationDTO getQualification(@Min(1) long id);
    List<QualificationDTO> getQualifications();
    Qualification create(@Valid CreateQualificationDTO qualification);
    Qualification updateQualification(long id, UpdateQualificationDTO qualification);
    void deleteQualification(long id);

    List<QualificationDTO> getQualificationsSortedByName(String sortDirection);

    Page<QualificationDTO> getSchoolsPagination(PageRequest pageable);
}
