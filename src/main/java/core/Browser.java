package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {

    public Browser(String browserName){
        launchBrowser(browserName);
    }


    public void launchBrowser(String browserName){

        switch(browserName.toUpperCase())
        {
            case "CHROME":
                WebDriverManager.chromedriver().setup();
                ChromeOptions option = new ChromeOptions();
                DesiredCapabilities capabilities=DesiredCapabilities.chrome();
                capabilities.setCapability(ChromeOptions.CAPABILITY,option);
                BrowserDriver.driver = new ChromeDriver(option);
                break;

            case "FIREFOX":
                WebDriverManager.firefoxdriver().setup();
                BrowserDriver.driver = new FirefoxDriver();
        }
        BrowserDriver.driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        BrowserDriver.driver.manage().window().maximize();
    }

//    public void launchWebsite(String browserName, String environment) throws IOException {
//        launchBrowser(browserName);
//        System.out.println(environment);
//        String portalURL = configReader.readProperties(environment.toUpperCase());
//        BrowserDriver.driver.navigate().to(portalURL);
//    }
}
