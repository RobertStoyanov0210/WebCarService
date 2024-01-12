package com.example.carservice.data.repository;

import com.example.carservice.data.entity.User;
import com.example.carservice.data.entity.Vehicle;
import com.example.carservice.dto.vehicle.CreateVehicleDTO;
import com.example.carservice.dto.vehicle.VehicleDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.security.core.Authentication;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class VehicleRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private VehicleRepository vehicleRepository;

    @Test
    void getVehiclesByOwnerIdEquals() {
        User user = new User();
        user.setUsername("username");
        user.setFirstName("fn");
        user.setPassword("1234");
        testEntityManager.persistAndFlush(user);
        User user2 = new User();
        user2.setUsername("username2");
        user2.setFirstName("fn2");
        user2.setPassword("1234");
        testEntityManager.persistAndFlush(user2);
        Vehicle vehicle = new Vehicle();
        Vehicle vehicle2 = new Vehicle();
        Vehicle vehicle3 = new Vehicle();
        vehicle.setOwner(user);
        vehicle2.setOwner(user);
        vehicle3.setOwner(user2);
        testEntityManager.persistAndFlush(vehicle);
        testEntityManager.persistAndFlush(vehicle2);
        testEntityManager.persistAndFlush(vehicle3);

        assertThat(vehicleRepository.getVehiclesByOwnerIdEquals(user.getId()).size()).isEqualTo(2);
    }

}
