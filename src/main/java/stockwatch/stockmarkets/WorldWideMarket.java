package stockwatch.stockmarkets;

import java.util.TimerTask;
import java.util.Vector;

import stockwatch.stockmarkets.descriptions.WSEDescription;

public class WorldWideMarket extends TimerTask {

    private Vector<StockMarket> stockExchanges;

    public WorldWideMarket() {
        stockExchanges = new Vector<StockMarket>();
        addStockMarkets();
    }

    private void addStockMarkets() {
        stockExchanges.add(new StockMarket(new WSEDescription()));
    }

    @Override
    public void run() {
        for (StockMarket stockExchange : stockExchanges) {
            stockExchange.updateQuotes().save();
        }
    }
}
