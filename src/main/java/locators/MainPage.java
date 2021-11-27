package locators;

import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MainPage {

    By currentTemperature = By.xpath("//span[@id='temperature']");
    By buyMoisturizers = By.xpath("//button[text()='Buy moisturizers']");
    By buySunscreens = By.xpath("//button[text()='Buy sunscreens']");

    public WebElement getCurrentTemperature(){
        return BrowserDriver.driver.findElement(currentTemperature);
    }

    public WebElement getMoisturizersButton(){
        return BrowserDriver.driver.findElement(buyMoisturizers);
    }

    public WebElement getSunscreensButton(){
        return BrowserDriver.driver.findElement(buySunscreens);
    }

}
