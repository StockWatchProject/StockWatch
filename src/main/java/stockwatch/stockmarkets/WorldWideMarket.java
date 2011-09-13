package stockwatch.stockmarkets;

import java.util.TimerTask;
import java.util.Vector;

import stockwatch.stockmarkets.parsers.WSEParser;

public class WorldWideMarket extends TimerTask {

    private Vector<StockMarket> stockExchanges;
    StockMarketBuilder builder;

    public WorldWideMarket() {
        stockExchanges = new Vector<StockMarket>();
        builder = new StockMarketBuilder();
        
        addStockMarkets();
    }

    private void addStockMarkets() {
        stockExchanges.add(builder.buildStockMarket(new StockMarket("Warsaw", new WSEParser())));
    }

    @Override
    public void run() {
        for (StockMarket stockExchange : stockExchanges) {
            stockExchange.updateQuotes().save();
        }
    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        return super.cancel();
    }

}
