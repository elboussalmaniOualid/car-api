package com.car.carApi.repository;

import com.car.carApi.model.Car;
import com.car.carApi.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByFuelTypeAndPriceLessThanEqual(FuelType fuelType, double price);
    List<Car> findByMake(String make);
    @Query("SELECT DISTINCT c.make FROM Car c")
    List<String> findAllCarMakes();
}