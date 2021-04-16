package tqs.p2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import tqs.p2.controller.CarController;
import tqs.p2.entity.Car;
import tqs.p2.service.CarManagerService;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(CarController.class)
public class CarRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarManagerService carManagerService;


    @Test
    public void test_getCarById_ReturnCar() throws Exception{

        given(carManagerService.getCarDetails(1l))
                .willReturn(Optional.of(new Car("Carro", "maker")));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("Carro"));
    }

    @Test
    public void test_getCarById_ReturnNotFoundException() throws Exception{

        given(carManagerService.getCarDetails(2l))
                .willReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void test_getAllCars_ReturnCarList() throws Exception{

        List<Car> carList = new ArrayList<>();
        carList.add(new Car("carro1","m1"));
        carList.add(new Car("carro2","m2"));
        carList.add(new Car("carro3","m3"));


        given(carManagerService.getAllCars()).willReturn(carList);

        mockMvc.perform(MockMvcRequestBuilders.get("/cars").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("carro1"))
                .andExpect(jsonPath("$[1].name").value("carro2"))
                .andExpect(jsonPath("$",hasSize(3)));
    }

    @Test
    public void test_createCar_ReturnCar() throws Exception{

        Car car = new Car("carro", "maker");
        given(carManagerService.saveCar(car)).willReturn(car);

        mockMvc.perform(MockMvcRequestBuilders.post("/cars").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.toJson(car)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(car.getName()));

    }

}
