package com.example.carservice.data.repository;

import com.example.carservice.data.entity.CarService;
import com.example.carservice.data.entity.Repair;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
public class RepairRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private RepairRepository repairRepository;

    @Test
    void findCurrentRepairsByCarService(){
        CarService carService = new CarService();
        carService.setName("cs");
        carService.setMaxRepairingCars(4);
        testEntityManager.persistAndFlush(carService);
        Repair repair1 = new Repair();
        repair1.setComplete(true);
        Repair repair2 = new Repair();
        Repair repair3 = new Repair();
        repair1.setCarService(carService);
        repair2.setCarService(carService);
        repair3.setCarService(carService);
        testEntityManager.persistAndFlush(repair1);
        testEntityManager.persistAndFlush(repair2);
        testEntityManager.persistAndFlush(repair3);
        carService.setVehiclesInRepair(Set.of(repair1,repair2,repair3));
        testEntityManager.persistAndFlush(carService);
        assertThat(repairRepository.findCurrentRepairsByCarService(carService.getId()).size()).isEqualTo(2);
    }
    //TODO when not found



}
