package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Brand;
import com.example.carservice.data.entity.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;


@DataJpaTest
public class CarServiceRepositoryTest {
    @Autowired
    private TestEntityManager testEntityManager;
    @Autowired
    private CarServiceRepository carServiceRepository;

    @Test
    void getCarServicesBySupportedBrandsContains(){
        CarService carService1 = new CarService();
        carService1.setName("cs1");
        carService1.setMaxRepairingCars(10);
        CarService carService2 = new CarService();
        carService2.setName("cs2");
        carService2.setMaxRepairingCars(7);
        Brand brand1 = new Brand();
        brand1.setName("b1");
        Brand brand2 = new Brand();
        brand2.setName("b2");
        testEntityManager.persistAndFlush(brand1);
        testEntityManager.persistAndFlush(brand2);
        carService1.setSupportedBrands(Set.of(brand1,brand2));
        carService2.setSupportedBrands(Set.of(brand2));
        testEntityManager.persistAndFlush(carService1);
        testEntityManager.persistAndFlush(carService2);

        assertThat(carServiceRepository.getCarServicesBySupportedBrandsContains(brand2).size()).isEqualTo(2);
    }
    //TODO when not found


}
