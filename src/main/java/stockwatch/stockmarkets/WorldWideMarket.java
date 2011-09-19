package stockwatch.stockmarkets;

import java.util.TimerTask;
import java.util.Vector;

public class WorldWideMarket extends TimerTask {

    private Vector<StockMarket> stockExchanges;

    public WorldWideMarket() {
        stockExchanges = new Vector<StockMarket>();
        addStockMarkets();
    }

    private void addStockMarkets() {
        MarketNames[] allMarketNames = MarketNames.values();
        for(MarketNames aMarket : allMarketNames){
            stockExchanges.add(new StockMarket(aMarket));
        }
    }

    @Override
    public void run() {
        for (StockMarket stockExchange : stockExchanges) {
            stockExchange.updateQuotes().save();
        }
    }
}
