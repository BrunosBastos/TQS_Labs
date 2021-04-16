package tqs.p2;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import tqs.p2.entity.Car;
import tqs.p2.repository.CarRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

// switch AutoConfigureTestDatabase with TestPropertySource to use a real database
//@TestPropertySource( locations = "application-integrationtest.properties")

@AutoConfigureTestDatabase
class P2ApplicationTests {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository repository;

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void whenValidInput_thenCreateCar() {
        Car bmw = new Car("bmw", "bmw");
        ResponseEntity<Car> entity = restTemplate.postForEntity("/cars", bmw, Car.class);

        List<Car> found = repository.findAll();
        assertThat(found).extracting(Car::getName).containsOnly("bmw");
    }

    @Test
    public void givenCars_whenGetCars_thenStatus200()  {
        createTestCar("bmw", "bmw");
        createTestCar("audi", "audi");


        ResponseEntity<List<Car>> response = restTemplate
                .exchange("/cars", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).extracting(Car::getName).containsExactly("bmw", "audi");

    }


    private void createTestCar(String name, String email) {
        Car emp = new Car(name, email);
        repository.saveAndFlush(emp);
    }

}
