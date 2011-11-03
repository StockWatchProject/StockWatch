package stockwatch;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Timer;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import stockwatch.stockmarkets.WorldWideMarket;
import config.ConfigParser;

public class Server {
    private static final Logger logger = Logger.getLogger(Server.class);
    private static final AtomicInteger threadIdGen = new AtomicInteger();
    
    private final int REPEAT_AFTER;
    private final int INIT_DELAY;
    private final int PORT_NUM;
    
    private ConfigParser configParser;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private WorldWideMarket worldWideMarket;
    
    public Server() {
        configParser    = new ConfigParser();

        REPEAT_AFTER = configParser.getRefreshRate();
        INIT_DELAY   = configParser.getInitialDelay();
        PORT_NUM     = configParser.getPortNum();
        
        logger.debug("Stockwatch server is up and listening on port: " + PORT_NUM + ". "
                + "Quotes will be parsed every: " + REPEAT_AFTER / 1000 
                + " seconds with initial delay " + INIT_DELAY + ".");
        
        worldWideMarket = new WorldWideMarket();
    }
    
    private void run() {
        Timer timer = new Timer();
        timer.schedule(worldWideMarket, INIT_DELAY, REPEAT_AFTER);
        
        try {
            serverSocket = new ServerSocket(PORT_NUM);
        } catch (IOException e) {
            logger.fatal("Could not listen on port: " + PORT_NUM + " " + e.getMessage());
            System.exit(-1);
        }
        
        while (true) {
            try {
                clientSocket = serverSocket.accept();
                new InvestorThread(clientSocket, worldWideMarket, threadIdGen.getAndIncrement()).start();
            } catch (IOException e) {
                logger.error("Accept problem!" + e.getMessage());
            }
        }
    }
    
    public static void main(String args[]) {
        Server server = new Server();
        server.run();
    }
}
