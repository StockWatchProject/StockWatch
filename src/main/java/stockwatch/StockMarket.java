package stockwatch;

import java.util.TimerTask;
import java.util.Vector;

public class StockMarket extends TimerTask {

    private Vector<StockExchange> stockExchanges;
    StockExchangeContextBuilder builder;

    public StockMarket() {
        stockExchanges = new Vector<StockExchange>();
        builder = new StockExchangeContextBuilder();
        
        addStockMarkets();
    }

    private void addStockMarkets() {
        stockExchanges.add(builder.buildStockMarket(new WarsawStockExchange()));
    }

    @Override
    public void run() {
        for (StockExchange stockExchange : stockExchanges) {
            stockExchange.updateQuotes().save();
        }
    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        return super.cancel();
    }

}
