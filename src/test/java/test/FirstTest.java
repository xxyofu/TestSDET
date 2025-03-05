package test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class FirstTest {
    WebDriver driver;
    LoginPage login;

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        try {
            driver.get("https://practice-automation.com/form-fields/");
        } catch (TimeoutException ignore){}
    }

    @Test
    public void First(){
        login = new LoginPage(driver);
        login.setName("TestName");
        login.setPassword("TestPassword");
        login.setDrinks(new LoginPage.DrinksEnum[]{LoginPage.DrinksEnum.Milk, LoginPage.DrinksEnum.Coffee});
        login.setColor(LoginPage.Colors.Yellow);
        login.setLoveAuto(1);
        login.setEmail("example@test.com");
        login.setMessage(String.format("Число инструментов: %d\nНаибольшее количество символов: %s", login.getAutos().length, login.getLongestAuto()));
        login.buttonClick();
        Alert alert = login.getAlert();
        Assertions.assertNotNull(alert);
        login.closeAlert();
    }

    @AfterEach
    public void endSession(){
        driver.close();
    }
}
