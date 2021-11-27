package locators;

import core.BrowserDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage2 {

    private BrowserDriver driver;

    @FindBy(xpath= "//span[@id='temperature']")
    WebElement currentTemperature;

    @FindBy(xpath = "//button[text()='Buy moisturizers']")
    WebElement buyMoisturizers;

    @FindBy(xpath = "//button[text()='Buy sunscreens']")
    WebElement buySunscreens;

    public MainPage2(WebDriver driver){
        this.driver = (BrowserDriver) driver;
        PageFactory.initElements(driver, this);
    }

    public String getCurrentTemperature(){

        return currentTemperature.getText();
    }
}
