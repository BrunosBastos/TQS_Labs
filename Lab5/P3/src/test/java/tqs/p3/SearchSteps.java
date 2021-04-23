package tqs.p3;

import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;


public class SearchSteps {

    private WebDriver webDriver;

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        webDriver = new FirefoxDriver();
        webDriver.get(url);
    }

    @And("I type {string}")
    public void iType(String searchQuery) {
        webDriver.findElement(By.name("q")).sendKeys(searchQuery);
    }

    @And("I press Enter")
    public void iPressEnter() {
        webDriver.findElement(By.name("q")).sendKeys(Keys.ENTER);
    }

    @Then("I should be shown results including {string}")
    public void iShouldBeShownResultsIncluding(String result) {
        try {
            webDriver.findElement(
                    By.xpath("//*[contains(text(), '" + result + "')]"));
        } catch (NoSuchElementException e) {
            throw new AssertionError(
                    "\"" + result + "\" not available in results");
        } finally {
            webDriver.close();
        }
    }

    @And("I choose {string} on the input {string}")
    public void iChooseOnTheInput(String arg0, String arg1) {
        webDriver.findElement(By.name(arg1)).click();
        {
            WebElement dropdown = webDriver.findElement(By.name(arg1));
            dropdown.findElement(By.xpath("//option[. = '" + arg0 + "']")).click();
        }

    }

    @And("I choose the flight {int}")
    public void iChooseTheFlight(int arg0) {
        webDriver.findElement(By.cssSelector("tr:nth-child("+arg0+") .btn")).click();
    }

    @And("I click on {string}")
    public void iClickOn(String arg0) {
        webDriver.findElement(By.xpath("//input[@type='submit' and @value='"+arg0+"']")).click();
    }
}