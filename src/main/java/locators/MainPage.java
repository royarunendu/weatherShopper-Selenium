package locators;

import core.Browser;
import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Browser {

    public MainPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
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
