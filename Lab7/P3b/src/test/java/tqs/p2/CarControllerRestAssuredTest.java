package tqs.p2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tqs.p2.controller.CarController;
import tqs.p2.entity.Car;
import tqs.p2.service.CarManagerService;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@WebMvcTest(CarController.class)
public class CarControllerRestAssuredTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CarManagerService carManagerService;

    @BeforeEach
    public void associateAssured() {
        RestAssuredMockMvc.mockMvc(mvc);
    }


    @Test
    public void test_GetAllCars_thenReturnAllCars() {
        Car bmw = new Car("bmw", "bmw");
        Car opel = new Car("opel", "opel");
        Car audi = new Car("audi", "audi");

        List<Car> allCars = Arrays.asList(bmw, opel, audi);

        when(carManagerService.getAllCars()).thenReturn(allCars);

        RestAssuredMockMvc.given().when()
                .get("/cars")
                .then().assertThat().statusCode(200)
                .and().body("", hasSize(3))
                .and().body("name[0]", is(bmw.getMaker()))
                .and().body("maker[0]", is(bmw.getName()))
                .and().body("name[1]", is(opel.getMaker()))
                .and().body("maker[1]", is(opel.getName()))
                .and().body("name[2]", is(audi.getMaker()))
                .and().body("maker[2]", is(audi.getName()));

        verify(carManagerService, times(1)).getAllCars();
    }
    @Test
    public void test_postCar_thenCreateCar() throws Exception {
        Car bmw = new Car("bmw", "bmw");
        when(carManagerService.saveCar(Mockito.any())).thenReturn(bmw);


        RestAssuredMockMvc.given().header("Content-Type", "application/json")
                .body(JsonUtil.toJson(bmw))
                .post("/cars")
                .then().assertThat().statusCode(200)
                .and().body("name", equalTo("bmw"))
                .and().body("maker", equalTo("bmw"));

        verify(carManagerService, times(1)).saveCar(bmw);
    }
    @Test
    public void test_getValidCarDetails_thenReturnCar() {
        Car bmw = new Car("bmw", "bmw");
        bmw.setCarId(1L);
        when(carManagerService.getCarDetails(1L)).thenReturn(Optional.of(bmw));

        RestAssuredMockMvc.given().when()
                .get("/cars/1")
                .then().assertThat().statusCode(200)
                .and().body("name", equalTo("bmw"))
                .and().body("maker", equalTo("bmw"));

        verify(carManagerService, times(1)).getCarDetails(bmw.getCarId());
    }

    @Test
    public void test_getInvalidCarDetails_thenReturnBadRequest() {
        RestAssuredMockMvc.given().when()
                .get("/cars/-1")
                .then().assertThat().statusCode(404);
        verify(carManagerService, times(1)).getCarDetails(-1L);
    }


}
