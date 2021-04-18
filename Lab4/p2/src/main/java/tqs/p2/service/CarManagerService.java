package tqs.p2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tqs.p2.entity.Car;
import tqs.p2.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarManagerService {

    @Autowired
    private CarRepository carRepository;

    public Car saveCar(Car car){
        return carRepository.save(car);
    }

    public List<Car> getAllCars(){
        return carRepository.findAll();
    }

    public Optional<Car> getCarDetails(Long carId){
        return carRepository.findById(carId);
    }

}
