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
    private int refreshRate;
    private int initialDelay;
    private int portNum;

    public ConfigParser() {
        try {
            PropertyConfigurator.configure(fileName);
            
            appProperties = new Properties();
            propertiesFile = new FileInputStream(fileName);
            
            appProperties.load(propertiesFile);
            
            refreshRate  = Integer.parseInt(appProperties.getProperty("StockWatch.refreshRate"));
            initialDelay = Integer.parseInt(appProperties.getProperty("StockWatch.initialDelay"));
            portNum      = Integer.parseInt(appProperties.getProperty("StockWatch.port"));
            
        } catch (FileNotFoundException e) {
            System.exit(-1);
        } catch (IOException e) {
            System.exit(-1);
        }
    }
    
    public int getRefreshRate() { return refreshRate; }
    
    public int getInitialDelay() { return initialDelay; }
    
    public int getPortNum() { return portNum; }
}