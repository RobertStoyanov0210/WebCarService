package com.example.carservice.data.repository;

import com.example.carservice.data.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Long> {
}
