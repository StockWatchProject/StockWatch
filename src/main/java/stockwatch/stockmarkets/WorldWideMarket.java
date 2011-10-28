package stockwatch.stockmarkets;

import java.util.TimerTask;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.messages.QuoteMessages.QuoteList.Builder;

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
    
    public synchronized QuoteList getQuotes(QuoteList quotesRequest) {
        Builder builder = QuoteList.newBuilder();
        for (StockMarket market : stockExchanges) {
            builder.mergeFrom(market.getQuotes(quotesRequest));
        }
        return builder.build();
    }
    
    public synchronized QuoteList getQuotes() {
        Builder builder = QuoteList.newBuilder();
        for (StockMarket market : stockExchanges) {
            builder.mergeFrom(market.getQuotes());
        }
        return builder.build();
    }
}
