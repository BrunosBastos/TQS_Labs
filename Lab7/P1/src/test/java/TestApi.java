import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class TestApi {

    @Test
    public void test_whenGetAll_thenReturnOk(){
        given().
        when().
                get("https://jsonplaceholder.typicode.com/todos").
        then().
                statusCode(200);
    }

    @Test
    public void test_whenGetAll_thenReturnExpectedAtGivenPosition(){
        given().
        when().
                get("https://jsonplaceholder.typicode.com/todos").
        then().
                body("[3].title", equalTo("et porro tempora"));
    }

    @Test
    public void test_whenGetAll_thenReturnExpectedIds(){
        given().
        when().
                get("https://jsonplaceholder.typicode.com/todos").
        then().
                body("id", hasItems(198,199));
    }

}
