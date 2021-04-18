package tqs.p2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tqs.p2.entity.Car;

public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByName(String name);
}
