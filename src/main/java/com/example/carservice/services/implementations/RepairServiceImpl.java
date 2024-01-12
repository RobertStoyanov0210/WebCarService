package com.example.carservice.services.implementations;

import com.example.carservice.data.entity.Repair;
import com.example.carservice.data.repository.RepairRepository;
import com.example.carservice.dto.repair.CreateRepairDTO;
import com.example.carservice.dto.repair.RepairDTO;
import com.example.carservice.dto.repair.UpdateRepairDTO;
import com.example.carservice.services.RepairService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Validated
@AllArgsConstructor
public class RepairServiceImpl implements RepairService {
    private final RepairRepository repairRepository;
    private final ModelMapper modelMapper;

    @Override
    public Set<RepairDTO> getCurrentRepairs(long id) {
        return repairRepository.findCurrentRepairsByCarService(id).stream()
                .map(r -> modelMapper.map(r,RepairDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public RepairDTO getRepair(@Min(1) long id) {
        return modelMapper.map(repairRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Repair id is invalid!")),RepairDTO.class);
    }

    @Override
    public Set<RepairDTO> getCustomerHistoryRepairs(long userId) {
        return repairRepository.findPastRepairsByUser(userId).stream()
                .map(r -> modelMapper.map(r,RepairDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Set<RepairDTO> getCarServiceHistoryRepairs(long carServiceId) {
        return  repairRepository.findPastRepairsByCarService(carServiceId).stream()
                .map(r -> modelMapper.map(r,RepairDTO.class))
                .collect(Collectors.toSet());
    }

    @Override
    public void createRepair(CreateRepairDTO createRepairDTO) {
        System.out.println("SAVING REPAIR");
//        System.out.println(modelMapper.map(createRepairDTO,Repair.class));
        repairRepository.save(modelMapper.map(createRepairDTO, Repair.class));
    }

    @Override
    public void finishRepair(long repairId) {
//        System.out.println("REPAIR SERVICE");
//        UpdateRepairDTO repairDTO = modelMapper.map(repairRepository.findById(repairId), UpdateRepairDTO.class);
//        repairDTO.setComplete(true);
//        System.out.println(repairDTO);
//        System.out.println(modelMapper.map(repairDTO,Repair.class));
        Repair repair = repairRepository.findById(repairId)
                .orElseThrow(() -> new NullPointerException("Repair invalid id"));
        repair.setComplete(true);
        repair.setFinishDate(LocalDate.now());
        repairRepository.save(modelMapper.map(repair,Repair.class));
    }
}
