package stockwatch;

import java.util.Timer;

import org.apache.log4j.Logger;

import stockwatch.stockmarkets.WorldWideMarket;
import config.ConfigParser;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class);
    private ConfigParser configParser;
    private final int REPEAT_AFTER;
    private final int INIT_DELAY;
    
    public Server() {
        configParser = new ConfigParser();
        REPEAT_AFTER = configParser.getRefreshRate();
        INIT_DELAY = 0;
    }
    
    private void run() {
        logger.debug("Quotes will be parsed every " + REPEAT_AFTER/1000 + 
                " seconds with initial delay " + INIT_DELAY  + ".");
        Timer timer = new Timer();
        // refresh quotes every 30 minutes
        timer.schedule(new WorldWideMarket(), INIT_DELAY, REPEAT_AFTER);
    }
    
    public static void main(String args[]) {
        Server server = new Server();
        server.run();
    }
}
