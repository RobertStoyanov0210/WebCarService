package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Qualification;
import com.example.carservice.data.repository.QualificationRepository;
import com.example.carservice.dto.qualification.CreateQualificationDTO;
import com.example.carservice.dto.qualification.QualificationDTO;
import com.example.carservice.dto.qualification.UpdateQualificationDTO;
import com.example.carservice.services.QualificationService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Validated
public class QualificationServiceImpl implements QualificationService {
    private final QualificationRepository qualificationRepository;
    private final ModelMapper modelMapper;

    @Override
    public QualificationDTO getQualification(@Min(1) long id) {
        return modelMapper.map(qualificationRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Qualification id is invalid!")), QualificationDTO.class);
    }

    @Override
    public List<QualificationDTO> getQualifications() {
        return qualificationRepository.findAll().stream()
                .map(q -> modelMapper.map(q, QualificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Qualification create(@Valid CreateQualificationDTO qualification) {
        return qualificationRepository.save(modelMapper.map(qualification, Qualification.class));
    }

    @Override
    public Qualification updateQualification(long id, UpdateQualificationDTO qualificationDTO) {
        Qualification qualification = modelMapper.map(qualificationDTO, Qualification.class);
        qualification.setId(id);
        return qualificationRepository.save(qualification);
    }
    @Override
    public void deleteQualification(long id){
        qualificationRepository.deleteById(id);
    }

    @Override
    public List<QualificationDTO> getQualificationsSortedByName(String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.DESC.name()) ? Sort.by(Sort.Direction.DESC, "name") : Sort.by("name");
        return qualificationRepository.findAll(sort).stream()
                .map(q -> modelMapper.map(q, QualificationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Page<QualificationDTO> getSchoolsPagination(PageRequest pageable) {
        List<QualificationDTO> qualificationList = qualificationRepository.findAll()
                .stream()
                .map(q -> modelMapper.map(q,QualificationDTO.class))
                .collect(Collectors.toList());
        int page = pageable.getPageNumber();
        int size = pageable.getPageSize();
        int firstSchoolNumber = page * size;

        List<QualificationDTO> qualificationPageList;

        if (qualificationList.size() < firstSchoolNumber) {
            qualificationPageList = Collections.emptyList();
        } else {
            int toIndex = Math.min(firstSchoolNumber + size, qualificationList.size());
            qualificationPageList = qualificationList.subList(firstSchoolNumber, toIndex);
        }

        Page<QualificationDTO> pageOfSchools
                = new PageImpl<QualificationDTO>(qualificationPageList, PageRequest.of(page, size), qualificationList.size());

        return pageOfSchools;
    }

}
