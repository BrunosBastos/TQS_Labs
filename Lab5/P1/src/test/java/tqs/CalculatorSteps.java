package tqs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class CalculatorSteps {

    private Calculator calculator;

    @When("I add {int} and {int}")
    public void iAddAnd(int arg0, int arg1) {
        calculator.push(arg0);
        calculator.push(arg1);
        calculator.push("+");
    }

    @When("I subtract {int} to {int}")
    public void iSubtractTo(int arg0, int arg1) {
        calculator.push(arg0);
        calculator.push(arg1);
        calculator.push("-");
    }


    @Then("the result is {int}")
    public void theResultIs(int arg0) {
        assertEquals(arg0, calculator.value());
    }

    @Given("a Calculator I just turned On")
    public void aCalculatorIJustTurnedOn() {
        calculator = new Calculator();
    }
}
