package stockwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;
import java.util.Vector;

public class StockMarket extends TimerTask {

    private Vector<StockExchange> stockExchanges;

    public StockMarket() {
        stockExchanges = new Vector<StockExchange>();
        addStockMarkets();
    }

    private void addStockMarkets() {
        ConfigParser parser = new ConfigParser("dotConfig");
        stockExchanges.add(new WarsawStockExchange(parser));
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
