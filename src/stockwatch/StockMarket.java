package stockwatch;

import java.util.TimerTask;
import java.util.Vector;

public class StockMarket extends TimerTask {

    private Vector<StockExchange> stockExchanges;
    private QuotesParser quotesParser;

    public StockMarket() {
        quotesParser = new WarsawStockExchangeParser();
        stockExchanges = new Vector<StockExchange>();
        addStockMarkets();
    }

    private void addStockMarkets() {
        stockExchanges.add(new WarsawStockExchange());
    }

    @Override
    public void run() {
        for (StockExchange stockExchange : stockExchanges) {
            stockExchange.updateQuotes(quotesParser.parseQuotes());
            stockExchange.getSessionStatistics();
        }
    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        return super.cancel();
    }

}
