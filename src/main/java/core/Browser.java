package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.getOSInformation;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Browser {

    ConfigReader configReader = new ConfigReader();
    public static WebDriver driver;

    public Browser(WebDriver driver){
        this.driver=driver;
    }

    public static WebDriver launchBrowser(String browserName){

        switch(browserName.toUpperCase())
        {
            case "CHROME":
//                String driverPath = "src/main/resources/browserDriver/chromedriver_" + (new getOSInformation()).getOS();
//                System.setProperty("webdriver.chrome.driver", driverPath);
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
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        return driver;
    }

//    public WebDriver launchWebsite(String browserName, String environment) throws IOException {
//        launchBrowser(browserName);
//        System.out.println(environment);
//        String portalURL = configReader.readProperties(environment.toUpperCase());
//        driver.navigate().to(portalURL);
//        return driver;
//    }
}
