package locators;

import core.BrowserDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage {


    By pageHeading = By.xpath("//body/div[@class='container']/div/h2");
    By productNames = By.xpath("//body/div[@class='container']/div/div/p[1]");
    By productPrices = By.xpath("//body/div[@class='container']/div/div/p[2]");
    By add = By.xpath("//body/div[@class='container']/div/div/button");
    By cart = By.xpath("//button[@onclick='goToCart()']/span");

    public WebElement getParentProduct(){
        return BrowserDriver.driver.findElement(pageHeading);
    }
    public List<WebElement> getProductNames(){
        return BrowserDriver.driver.findElements(productNames);
    }

    public List<WebElement> getproductPrices(){
        return BrowserDriver.driver.findElements(productPrices);
    }

    public List<WebElement> getAddButton(){
        return BrowserDriver.driver.findElements(add);
    }

    public WebElement cartButton(){
        return BrowserDriver.driver.findElement(cart);
    }

}
