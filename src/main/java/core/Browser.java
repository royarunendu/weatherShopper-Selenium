package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.util.concurrent.TimeUnit;

public class Browser {

    public WebDriver driver;

    public WebDriver launchBrowser(String browserName){

        switch(browserName.toUpperCase())
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions option = new ChromeOptions();
                DesiredCapabilities capabilities=DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY,option);
                driver = new ChromeDriver(option);
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
        }
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }
}
