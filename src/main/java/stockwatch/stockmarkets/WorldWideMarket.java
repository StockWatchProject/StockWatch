package stockwatch.stockmarkets;

import java.util.List;
import java.util.TimerTask;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.google.common.base.Predicate;

import stockwatch.Utils;
import stockwatch.messages.QuoteMessages.Quote;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.messages.QuoteMessages.QuoteList.Builder;

public class WorldWideMarket extends TimerTask {
    private static final Logger logger = Logger.getLogger(WorldWideMarket.class);
    private Vector<StockMarket> stockExchanges;
    
    private class MarketChecker implements Predicate<Quote> {
        private List<Integer> ids;
        public MarketChecker(List<Integer> ids) { this.ids = ids; }
        public boolean apply(Quote quote) { return ids.contains(quote.getMarketId()); }
    };
    
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
            QuoteList filteredQuotes = 
                    QuoteList
                    .newBuilder()
                    .addAllQuote(Utils.findIf(quotesRequest.getQuoteList(), new MarketChecker(market.getInternalMarketIds())))
                    .build();
            builder.mergeFrom(market.getQuotes(filteredQuotes));
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
