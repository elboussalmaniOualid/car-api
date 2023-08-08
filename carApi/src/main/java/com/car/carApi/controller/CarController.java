package com.car.carApi.controller;



import com.car.carApi.model.Car;
import com.car.carApi.model.FuelType;
import com.car.carApi.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public ResponseEntity<Car> addCar(@RequestBody Car car) {
        Car addedCar = carService.addCar(car);
        return ResponseEntity.ok(addedCar);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Car>> getCarsByFuelTypeAndMaxPrice(@RequestParam FuelType fuelType, @RequestParam double maxPrice) {
        List<Car> cars = carService.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/makes")
    public ResponseEntity<List<String>> getAllCarMakes() {
        List<String> carMakes = carService.getAllCarMakes();
        return ResponseEntity.ok(carMakes);
    }

    @PutMapping("/{carId}/picture")
    public ResponseEntity<Car> updateCarPicture(@PathVariable Long carId, @RequestParam String pictureUrl) {
        Car updatedCar = carService.updateCarPicture(carId, pictureUrl);
        return ResponseEntity.ok(updatedCar);
    }
}