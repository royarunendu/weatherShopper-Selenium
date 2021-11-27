package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    public String configFilePath= "src/main/resources/config.properties";

    public String readProperties(String key) throws IOException {
        Properties prop = new Properties();
        InputStream input = null;
        try {
            input = new FileInputStream(configFilePath);
            prop.load(input);
            return prop.getProperty(key);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
