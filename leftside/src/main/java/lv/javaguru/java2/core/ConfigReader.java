package lv.javaguru.java2.core;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by SM on 11/3/2014.
 */
public final class ConfigReader {
    private static final String CONFIG_FILE = "app.properties";
    Properties properties = new Properties();

    public ConfigReader() {
     try {
        properties.load(ConfigReader.class.getClassLoader().getResourceAsStream(CONFIG_FILE));
    } catch (IOException e) {
        System.out.println("Exception while reading configuration from file = " + CONFIG_FILE);
        e.printStackTrace();
    }
    }

    public boolean isDebugMode() {
        return properties.getProperty("debug", "false")
                .equals("true");
    }


}
