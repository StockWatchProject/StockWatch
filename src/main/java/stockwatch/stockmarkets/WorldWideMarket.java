package stockwatch.stockmarkets;

import java.util.TimerTask;
import java.util.Vector;

import org.apache.log4j.Logger;

public class WorldWideMarket extends TimerTask {
    private static final Logger logger = Logger.getLogger(WorldWideMarket.class);
    private Vector<StockMarket> stockExchanges;
    
    public WorldWideMarket() {
        stockExchanges = new Vector<StockMarket>();
        addStockMarkets();
    }

    private void addStockMarkets() {
        MarketNames[] allMarketNames = MarketNames.values();
        for(MarketNames market : allMarketNames){
            stockExchanges.add(new StockMarket(market));
            logger.debug("Added " + market.name() + " to market collection.");
        }
    }

    @Override
    public void run() {
        for (StockMarket stockExchange : stockExchanges) {
            stockExchange.updateQuotes();
        }
    }
}
