package locators;

import core.Browser;
import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductPage extends Browser {

    public ProductPage(WebDriver driver){
        super(driver);
        PageFactory.initElements(driver, this);
    }


    By pageHeading = By.xpath("//body/div[@class='container']/div/h2");
    By productNames = By.xpath("//body/div[@class='container']/div/div/p[1]");
    By productPrices = By.xpath("//body/div[@class='container']/div/div/p[2]");
    By add = By.xpath("//body/div[@class='container']/div/div/button");
    By cart = By.xpath("//button[@onclick='goToCart()']/span");

    public WebElement getParentProduct(){
        return driver.findElement(pageHeading);
    }
    public List<WebElement> getProductNames(){
        return driver.findElements(productNames);
    }

    public List<WebElement> getproductPrices(){
        return driver.findElements(productPrices);
    }

    public List<WebElement> getAddButton(){
        return driver.findElements(add);
    }

    public WebElement cartButton(){
        return driver.findElement(cart);
    }

}
