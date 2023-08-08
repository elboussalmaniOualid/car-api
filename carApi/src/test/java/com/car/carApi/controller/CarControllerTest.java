package com.car.carApi.controller;

import com.car.carApi.model.Car;
import com.car.carApi.model.FuelType;
import com.car.carApi.model.TransmissionType;
import com.car.carApi.service.CarService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class)
@AutoConfigureMockMvc
public class CarControllerTest {


    @InjectMocks
    private CarController carController;

    @MockBean
    private CarService carService;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testAddCar() {
        Car carToAdd = new Car(1L, "Toyota", "Camry", LocalDate.now(), 25000.0, FuelType.DIESEL, 50000, TransmissionType.AUTOMATIC, null);
        when(carService.addCar(carToAdd)).thenReturn(carToAdd);

        ResponseEntity<Car> response = carController.addCar(carToAdd);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carToAdd.getMake(), response.getBody().getMake());
    }

    @Test
    public void testGetCarsByFuelTypeAndMaxPrice() {
        FuelType fuelType = FuelType.DIESEL;
        double maxPrice = 30000.0;
        List<Car> cars = Arrays.asList(
                new Car(1L, "Toyota", "Corolla", LocalDate.now(), 20000.0, FuelType.DIESEL, 60000, TransmissionType.MANUAL, null),
                new Car(2L, "Honda", "Civic", LocalDate.now(), 28000.0, FuelType.DIESEL, 55000, TransmissionType.AUTOMATIC, null)
        );
        when(carService.getCarsByFuelTypeAndMaxPrice(eq(fuelType), eq(maxPrice))).thenReturn(cars);

        ResponseEntity<List<Car>> response = carController.getCarsByFuelTypeAndMaxPrice(fuelType, maxPrice);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cars.size(), response.getBody().size());
        assertEquals(cars.get(0).getMake(), response.getBody().get(0).getMake());
    }

    @Test
    public void testGetAllCarMakes() {
        List<String> carMakes = Arrays.asList("Toyota", "Honda", "Ford");
        when(carService.getAllCarMakes()).thenReturn(carMakes);

        ResponseEntity<List<String>> response = carController.getAllCarMakes();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(carMakes.size(), response.getBody().size());
        assertEquals(carMakes.get(0), response.getBody().get(0));
    }

    @Test
    public void testUpdateCarPicture() {
        Long carId = 1L;
        String picture = "picture.jpg";
        Car car = new Car(1L, "Toyota", "Camry", LocalDate.now(), 25000.0, FuelType.DIESEL, 50000, TransmissionType.AUTOMATIC, null);
        Car carResp = new Car(1L, "Toyota", "Camry", LocalDate.now(), 25000.0, FuelType.DIESEL, 50000, TransmissionType.AUTOMATIC, picture);

        when(carService.updateCarPicture(eq(carId), eq(picture))).thenReturn(carResp);

        ResponseEntity<Car> response = carController.updateCarPicture(carId, picture);

        assertNotNull(response);
        assertEquals(response.getBody().getPictureUrl(), carResp.getPictureUrl());
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
