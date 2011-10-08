package config;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

public class ConfigParser {
    private final static String fileName = System.getProperty("user.home") + "/.dotConfig";

    private Properties appProperties;
    private InputStream propertiesFile;

    public ConfigParser() {
        try {
            appProperties = new Properties();
            propertiesFile = new FileInputStream(fileName);
            
            appProperties.load(propertiesFile);
            PropertyConfigurator.configure(fileName);
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}