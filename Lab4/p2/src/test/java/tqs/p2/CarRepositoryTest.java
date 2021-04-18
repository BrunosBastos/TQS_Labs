package tqs.p2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import tqs.p2.entity.Car;
import tqs.p2.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository carRepository;

    @Test
    public void test_whenFindCarByName_thenReturnCar(){

        Car car = new Car("car", "maker");
        entityManager.persistAndFlush(car);

        Car found = carRepository.findByName("car");
        assertThat(found).isEqualTo(car);

    }

    @Test
    public void test_whenInvalidCarName_thenReturnNull(){
        Car fromDb = carRepository.findByName("Does Not Exist");
        assertThat(fromDb).isNull();
    }

    @Test
    public void test_whenValidId_thenReturnCar(){
        Car car = new Car("test", "test");
        entityManager.persistAndFlush(car);

        Car fromDb = carRepository.findById(car.getCarId()).orElse(null);
        assertThat(fromDb).isNotNull();
        assertThat(fromDb.getName()).isEqualTo(car.getName());
    }

    @Test
    public void whenInvalidId_thenReturnNull() {
        Car fromDb = carRepository.findById(-111L).orElse(null);
        assertThat(fromDb).isNull();
    }

    @Test
    public void givenSetOfCars_whenFindAll_thenReturnAllCars() {
        Car bmw = new Car("bmw", "bmw");
        Car audi = new Car("audi", "audi");
        Car opel = new Car("opel", "opel");

        entityManager.persist(bmw);
        entityManager.persist(opel);
        entityManager.persist(audi);
        entityManager.flush();

        List<Car> allCars = carRepository.findAll();

        assertThat(allCars).hasSize(3).extracting(Car::getName).containsOnly(bmw.getName(), audi.getName(), opel.getName());
    }
}
