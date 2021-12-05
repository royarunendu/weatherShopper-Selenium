package locators;

import core.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ConfirmationPage extends Browser {

    WebDriver driver;
    public ConfirmationPage(WebDriver driver){
        this.driver=driver;
    }

    By confirmationHeader = By.xpath("//div[@class='row justify-content-center']/h2[text()='PAYMENT SUCCESS']");

    public WebElement getConfirmationHeader(){
        return driver.findElement(confirmationHeader);
    }
}
