package locators;

import core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainPage extends Browser {

    WebDriver driver;
    public MainPage(WebDriver driver){
        this.driver=driver;
    }

    By currentTemperature = By.xpath("//span[@id='temperature']");
    By buyMoisturizers = By.xpath("//button[text()='Buy moisturizers']");
    By buySunscreens = By.xpath("//button[text()='Buy sunscreens']");

    public WebElement getCurrentTemperature(){
        return driver.findElement(currentTemperature);
    }

    public WebElement getMoisturizersButton(){
        return driver.findElement(buyMoisturizers);
    }

    public WebElement getSunscreensButton(){
        return driver.findElement(buySunscreens);
    }

}
