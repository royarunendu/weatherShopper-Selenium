package core;

import java.io.IOException;

public class LaunchWebsite {

    ConfigReader configReader = new ConfigReader();

    public LaunchWebsite(String browserName, String testEnv)
    {
        try {
            new Browser(browserName);
            BrowserDriver.driver.navigate().to(configReader.readProperties(testEnv.toUpperCase()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
