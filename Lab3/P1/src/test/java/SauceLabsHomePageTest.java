import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.*;

class SauceLabsHomePageTest {

    WebDriver browser;
    @BeforeEach
    public void startUp(){
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        browser = new FirefoxDriver();
    }

    @AfterEach
    public void tearDown(){
        browser.close();
    }

    @org.junit.jupiter.api.Test
    public void site_header_is_on_home_page() {

        browser.get("https://www.saucelabs.com");
        WebElement href = browser.findElement(By.xpath("//a[@href='https://accounts.saucelabs.com/']"));
        assertTrue((href.isDisplayed()));

    }
}