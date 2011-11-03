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
            
            this.appProperties = new Properties();
            this.propertiesFile = new FileInputStream(fileName);
            
            this.appProperties.load(propertiesFile);
            
            this.refreshRate  = Integer.parseInt(appProperties.getProperty("StockWatch.refreshRate"));
            this.initialDelay = Integer.parseInt(appProperties.getProperty("StockWatch.initialDelay"));
            this.portNum      = Integer.parseInt(appProperties.getProperty("StockWatch.port"));
            
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