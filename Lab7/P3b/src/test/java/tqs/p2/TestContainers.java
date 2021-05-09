package tqs.p2;

import tqs.p2.entity.Car;
import tqs.p2.repository.CarRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import io.restassured.RestAssured;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class TestContainers {

    @LocalServerPort
    int randomServerPort;

    @Autowired
    private CarRepository repository;

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres")
            .withUsername("admin")
            .withPassword("password")
            .withDatabaseName("test");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @AfterEach
    public void resetDb() {
        repository.deleteAll();
    }


    @Test
    public void whenPostCar_thenCreateCar() throws Exception {
        Car bmw = new Car("bmw", "bmw");
        repository.saveAndFlush(bmw);

        RestAssured.given().header("Content-Type", "application/json")
                .body(JsonUtil.toJson(bmw))
                .post(getBaseUrl()+"/cars")
                .then().assertThat().statusCode(200)
                .and().body("name", equalTo("bmw"))
                .and().body("maker", equalTo("bmw"));
    }

    @Test
    public void testGetValidCarDetails() {
        Car bmw = new Car("bmw", "bmw");
        repository.saveAndFlush(bmw);


        RestAssured.given().when()
                .get(getBaseUrl()+"/cars/"+bmw.getCarId())
                .then().assertThat().statusCode(200)
                .and().body("name", equalTo("bmw"))
                .and().body("maker", equalTo("bmw"));
    }

    @Test
    public void testGetInvalidCarDetails() {
        RestAssured.given().when()
                .get(getBaseUrl()+"/cars/-1")
                .then().assertThat().statusCode(404);
    }

    @Test
    public void testGetAllCars() {
        createTestCar("bmw", "bmw");
        createTestCar("opel", "opel");
        createTestCar("audi", "audi");

        RestAssured.given().when()
                .get(getBaseUrl()+"/cars")
                .then().assertThat().statusCode(200)
                .and().body("", hasSize(3))
                .and().body("name[0]", is("bmw"))
                .and().body("maker[0]", is("bmw"))
                .and().body("name[1]", is("opel"))
                .and().body("maker[1]", is("opel"))
                .and().body("name[2]", is("audi"))
                .and().body("maker[2]", is("audi"));
    }


    private void createTestCar(String name, String maker) {
        Car car = new Car(name, maker);
        repository.saveAndFlush(car);
    }

    public String getBaseUrl() {
        return "http://localhost:"+randomServerPort;
    }
}
