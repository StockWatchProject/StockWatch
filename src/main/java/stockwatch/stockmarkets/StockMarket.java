package stockwatch.stockmarkets;

import java.util.List;

import org.apache.log4j.Logger;

import stockwatch.Utils;
import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.messages.QuoteMessages.Quote;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.messages.QuoteMessages.QuoteList.Builder;
import stockwatch.stockmarkets.descriptions.DescriptionsFactory;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;


public class StockMarket {
    private static final Logger logger = Logger.getLogger(StockMarket.class);
    private MarketNames name;
    private List<InternalMarket> internalMarkets;
    private QuotesParser quotesParser;
    
    private class MarketChecker implements Predicate<Quote> {
        private int id;
        public MarketChecker(int id) { this.id = id; }
        public boolean apply(Quote quote) { return quote.getMarketId() == this.id; }
    };
    
    public StockMarket(MarketNames mName){
        IStockMarketDescription description = DescriptionsFactory.getDescription(mName);
        StockMarketBuilder.getInstnce().buildStockMarket(this, description);
    }
    
    void setName(MarketNames name) {
        this.name = name;
    }
    
    void setParser(QuotesParser parser) {
        this.quotesParser = parser;
    }
    
    void setInternalMarkets(List<InternalMarket> internalMarkets) {
        this.internalMarkets = internalMarkets;
    }
    
    public MarketNames getName(){
        return name;
    }
    
    public void save() {
        //TODO: be or not to be %)
    }
    
    public List<Integer> getInternalMarketIds() {
        List<Integer> ids = Lists.newArrayList();
        for (InternalMarket market : internalMarkets)
            ids.add(market.getId());
        return ids;
    }
    
    public StockMarket updateQuotes() {
        try {
            internalMarkets = quotesParser.parseQuotes();
            // update also statistics for every market
            for (InternalMarket market : internalMarkets) {
                market.makeStatistics();
            }
            logger.debug("Quotes upaded.");
        } catch (SecuritiesParsingException e) {
            logger.error("Couldn't update quotes, cause: " + e.getMessage(), e);
        }
        return this;
    }

    /* Method returns requested quotes */
    public QuoteList getQuotes(QuoteList quotesRequest) {
        Builder builder = QuoteList.newBuilder();
        
        for (InternalMarket market : internalMarkets) {
            builder.mergeFrom(market.getQuotes(Utils.findIf(quotesRequest.getQuoteList(), new MarketChecker(market.getId()))));
        }
        logger.debug("List of: " + builder.getQuoteList().size() + " quotes from " + name + " will be sent.");
        return builder.build();
    }

    /* Method returns requested quotes */
    public QuoteList getQuotes() {
        Builder builder = QuoteList.newBuilder();
        
        for (InternalMarket market : internalMarkets) {
            builder.mergeFrom(market.getQuotes());
        }
        logger.debug("List of: " + builder.getQuoteList().size() + " quotes from " + name + " will be sent.");
        return builder.build();
    }
    
}
