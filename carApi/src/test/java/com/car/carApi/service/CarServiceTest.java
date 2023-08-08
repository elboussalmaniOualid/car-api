package com.car.carApi.service;

import com.car.carApi.model.Car;
import com.car.carApi.model.FuelType;
import com.car.carApi.model.TransmissionType;
import com.car.carApi.repository.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    @Test
    public void testAddCar() {
        Car carToAdd = new Car(1L, "Camry","Camry", LocalDate.now(), 25000.0, FuelType.DIESEL, 50000, TransmissionType.AUTOMATIC, null);
        when(carRepository.save(carToAdd)).thenReturn(carToAdd);

        Car addedCar = carService.addCar(carToAdd);

        assertNotNull(addedCar);
        assertEquals(carToAdd.getMake(), addedCar.getMake());
    }

    @Test
    public void testGetCarsByFuelTypeAndMaxPrice() {
        FuelType fuelType = FuelType.DIESEL;
        double maxPrice = 30000.0;
        List<Car> cars = Arrays.asList(
                new Car(1L, "Toyota", "Corolla", LocalDate.now(), 20000.0, FuelType.DIESEL, 60000, TransmissionType.AUTOMATIC, null),
                new Car(2L, "Honda", "Civic", LocalDate.now(), 28000.0, FuelType.DIESEL, 55000, TransmissionType.MANUAL, null)
        );
        when(carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice)).thenReturn(cars);

        List<Car> resultCars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);

        assertNotNull(resultCars);
        assertEquals(cars.size(), resultCars.size());
    }

}
