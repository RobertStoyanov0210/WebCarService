package com.example.carservice.services;

import com.example.carservice.data.entity.Repair;
import com.example.carservice.dto.repair.CreateRepairDTO;
import com.example.carservice.dto.repair.RepairDTO;

import javax.validation.constraints.Min;
import java.util.List;
import java.util.Set;

public interface RepairService {
    Set<RepairDTO> getCurrentRepairs(@Min(1) long id);
    RepairDTO getRepair(@Min(1) long id);
    Set<RepairDTO> getCustomerHistoryRepairs(long userId);
    Set<RepairDTO> getCarServiceHistoryRepairs(long carServiceId);


    void createRepair(CreateRepairDTO createRepairDTO);

    void finishRepair(long repairId);
}
