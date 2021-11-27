package locators;

import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage {


    By confirmationHeader = By.xpath("//div[@class='row justify-content-center']/h2[text()='PAYMENT SUCCESS']");

    public WebElement getConfirmationHeader(){
        return BrowserDriver.driver.findElement(confirmationHeader);
    }
}
