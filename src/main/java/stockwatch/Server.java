package stockwatch;

import java.util.Timer;

import stockwatch.stockmarkets.WorldWideMarket;

public class Server {
    private static final int REPEAT_AFTER = 30 * 1000;
    private static final int INIT_DELAY = 0;

    public static void main(String args[]) {
        Timer timer = new Timer();
        // refresh quotes every 30 minutes
        timer.schedule(new WorldWideMarket(), INIT_DELAY, REPEAT_AFTER);
    }
}
