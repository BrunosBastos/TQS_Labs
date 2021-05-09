package tqs.p3;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;
public class RestAssuredTest {
    @Test
    public void verifyEndpointIsAvailable() {
        when().
        get("https://jsonplaceholder.typicode.com/todos")
        .then()
                .assertThat().statusCode(200);
    }

    @Test
    public void verifyEndpointSpecificTodoReturnTitle() {
        when().
        get("https://jsonplaceholder.typicode.com/todos/4")
        .then()
            .assertThat()
            .statusCode(200)
            .and().body("title", equalTo("et porro tempora"))
            .and().body("id", equalTo(4));
    }

    @Test
    public void verifyEndpointReturnIds() {
        when().
        get("https://jsonplaceholder.typicode.com/todos")
        .then()
            .assertThat()
            .statusCode(200)
            .body("id", hasItems(198, 199));
    }
}
