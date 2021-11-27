package locators;

import core.Browser;
import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ConfirmationPage extends Browser {

    public ConfirmationPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }

    By confirmationHeader = By.xpath("//div[@class='row justify-content-center']/h2[text()='PAYMENT SUCCESS']");

    public WebElement getConfirmationHeader(){
        return driver.findElement(confirmationHeader);
    }
}
