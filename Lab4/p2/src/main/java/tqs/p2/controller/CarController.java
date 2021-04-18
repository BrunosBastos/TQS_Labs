package tqs.p2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tqs.p2.entity.Car;
import tqs.p2.service.CarManagerService;

import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private CarManagerService carService;

    public CarController(CarManagerService carService){
        this.carService = carService;
    }

    @PostMapping("/cars")
    private ResponseEntity<Car> createCar(@RequestBody Car car){
        return new ResponseEntity<>(carService.saveCar(car), HttpStatus.OK);
    }

    @GetMapping("/cars")
    private List<Car> getAllCars(){
        return carService.getAllCars();
    }

    @GetMapping("/cars/{id}")
    private ResponseEntity<Car> getCarById(@PathVariable Long id){
            Optional<Car> car = carService.getCarDetails(id);
            if(!car.isEmpty()){
                return new ResponseEntity<Car>(car.get(), HttpStatus.OK);
            }
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
