package com.example.carservice.data.repository;

import com.example.carservice.data.entity.CarServicePricelist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarServicePriceListRepository extends JpaRepository<CarServicePricelist,Long> {
}
