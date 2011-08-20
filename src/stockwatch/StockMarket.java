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
        ConfigParser parser = new ConfigParser("dotConfig");
        stockExchanges.add(new WarsawStockExchange(parser));
    }

    @Override
    public void run() {

        for (StockExchange stockExchange : stockExchanges) {
            stockExchange.updateQuotes(quotesParser.parseQuotes(), true);
            stockExchange.makeSessionStatistics(true);
        }

    }

    @Override
    public boolean cancel() {
        // TODO Auto-generated method stub
        return super.cancel();
    }

}
