package stockwatch;

import java.util.Timer;

public class Server {
    private static final int repeatAfter = 60 * 1000 * 5;
    private static final int initDelay = 0;

    public static void main(String args[]) {
        Timer timer = new Timer();
        // refresh quotes every 5 minutes
        timer.schedule(new StockMarket(), initDelay, repeatAfter);
    }
}
