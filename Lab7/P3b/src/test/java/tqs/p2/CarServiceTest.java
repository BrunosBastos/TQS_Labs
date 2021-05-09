package tqs.p2;


import org.assertj.core.api.CharArrayAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.VerificationModeFactory;
import org.mockito.junit.jupiter.MockitoExtension;
import tqs.p2.entity.Car;
import tqs.p2.repository.CarRepository;
import tqs.p2.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarServiceTest {

    // lenient is required because we load some expectations in the setup
    // that are not used in all the tests. As an alternative, the expectations
    // could move into each test method and be trimmed: no need for lenient
    @Mock( lenient = true)
    private CarRepository carRepository;

    @InjectMocks
    private CarManagerService carManagerService;

    @BeforeEach
    public void setUp() {
        Car bmw = new Car("bmw", "bmw");
        bmw.setCarId(1l);

        Car audi = new Car("audi", "audi");
        Car opel = new Car("opel", "opel");

        List<Car> allCars = Arrays.asList(bmw, audi, opel);

        Car test = new Car("test", "test");

        when(carRepository.findById(bmw.getCarId())).thenReturn(Optional.of(bmw));
        when(carRepository.findAll()).thenReturn(allCars);
        when(carRepository.findById(-99L)).thenReturn(Optional.empty());
        when(carRepository.save(test)).thenReturn(test);
    }

    @Test
    public void test_whenValidId_thenReturnCar() throws Exception{

        Optional<Car> fromDb = carManagerService.getCarDetails(1l);
        assertThat(fromDb.get().getName()).isEqualTo("bmw");
        verify(carRepository, VerificationModeFactory.times(1))
                .findById(anyLong());
    }

    @Test
    public void test_whenInvalidId_thenCarNotFound() throws Exception{
        Optional<Car> fromDb = carManagerService.getCarDetails(-99L);
        verify(carRepository, VerificationModeFactory.times(1))
                .findById(anyLong());
        assertThat(fromDb.isEmpty()).isEqualTo(true);

    }

    @Test
    public void test_getAllCars_thenReturnList() throws Exception{
        List<Car> fromDb = carManagerService.getAllCars();
        assertThat(fromDb.size()).isEqualTo(3);
        verify(carRepository, VerificationModeFactory.times(1))
                .findAll();
    }

    @Test
    public void test_saveCar_thenReturnCar() throws Exception{

        Car test = new Car("test", "test");
        Car fromDb = carManagerService.saveCar(test);
        assertThat(fromDb).isEqualTo(test);
        verify(carRepository, VerificationModeFactory.times(1))
                .save(test);

    }

}
