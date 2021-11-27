package locators;

import core.Browser;
import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class CheckoutPage {

    By checkoutHeading = By.xpath("//h2[text()='Checkout']");
    By payWithCard = By.xpath("//button/span[text()='Pay with Card']");
    By totalPrice = By.xpath("//p[@id='total']");
    By itemName = By.xpath("//tbody/tr/td[1]");
    By itemPrice = By.xpath("//tbody/tr/td[2]");
    By emailId = By.xpath("//input[@id='email']");
    By cardNumber = By.xpath("//input[@id='card_number']");
    By expiryDate = By.xpath("//input[@id='cc-exp']");
    By cvv = By.xpath("//input[@id='cc-csc']");
    By payButton = By.xpath("//span[@class='iconTick']");
    By zipCode = By.xpath("//input[@id='billing-zip']");

    public List<WebElement> getItemNames(){
        return BrowserDriver.driver.findElements(itemName);
    }
    public List<WebElement> getItemPrices(){
        return BrowserDriver.driver.findElements(itemPrice);
    }

    public WebElement getTotalPrice(){
        return BrowserDriver.driver.findElement(totalPrice);
    }

    public WebElement getPayWithCardButton(){
        return BrowserDriver.driver.findElement(payWithCard);
    }

    public void enterCardDetailsAndPay(String EmailId,  String CardNUmber, String ExpiryDate, String CVV) throws InterruptedException {

        int frameSize = BrowserDriver.driver.findElements(By.tagName("iframe")).size();
        for(int i=0;i<frameSize;i++){
            BrowserDriver.driver.switchTo().frame(i);
            Thread.sleep(5000);
            if(BrowserDriver.driver.findElement(By.xpath("//h1[text()='Stripe.com']")).isDisplayed()){
                BrowserDriver.driver.findElement(emailId).sendKeys(EmailId);
                for(char ch : CardNUmber.toCharArray()) {
                    BrowserDriver.driver.findElement(cardNumber).sendKeys(String.valueOf(ch));
                }
                Thread.sleep(1000);
                for(char ch : ExpiryDate.toCharArray()) {
                    BrowserDriver.driver.findElement(expiryDate).sendKeys(String.valueOf(ch));
                }
                BrowserDriver.driver.findElement(cvv).sendKeys(CVV);
                if(BrowserDriver.driver.findElement(zipCode).isDisplayed()){
                    BrowserDriver.driver.findElement(zipCode).click();
                    BrowserDriver.driver.findElement(zipCode).sendKeys("111111");
                }
                BrowserDriver.driver.findElement(payButton).click();
                BrowserDriver.driver.switchTo().parentFrame();
                break;
            }
        }

    }


}
