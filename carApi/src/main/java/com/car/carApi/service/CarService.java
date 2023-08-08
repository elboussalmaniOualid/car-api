package com.car.carApi.service;

import com.car.carApi.model.Car;
import com.car.carApi.model.FuelType;
import com.car.carApi.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public Car addCar(Car car) {
        // Ajouter des vérifications pour s'assurer que la voiture peut être ajoutée
        if (car.getRegistrationDate().isAfter(LocalDate.of(2015, 1, 1))) {
            return carRepository.save(car);
        } else {
            throw new IllegalArgumentException("Only cars registered after 2015 are allowed.");
        }
    }

    public List<Car> getCarsByFuelTypeAndMaxPrice(FuelType fuelType, double maxPrice) {
        return carRepository.findByFuelTypeAndPriceLessThanEqual(fuelType, maxPrice);
    }

    public List<String> getAllCarMakes() {
        return carRepository.findAllCarMakes();
    }

    public Car updateCarPicture(Long carId, String pictureUrl) {
        Car car = carRepository.findById(carId)
                .orElseThrow(() -> new IllegalArgumentException("Car not found with ID: " + carId));

        car.setPictureUrl(pictureUrl);
        return carRepository.save(car);
    }
}
