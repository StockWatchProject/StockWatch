package stockwatch.stockmarkets;

import java.util.List;

import org.apache.log4j.Logger;

import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.stockmarkets.descriptions.DescriptionsFactory;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;

public class StockMarket {
    private static final Logger logger = Logger.getLogger(StockMarket.class);
    private MarketNames name;
    private List<InternalMarket> internalMarkets;
    private QuotesParser quotesParser;
    
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
    
    public StockMarket updateQuotes() {
        try {
            internalMarkets = quotesParser.parseQuotes();
            // update also statistics for every market
            for (InternalMarket market : internalMarkets) {
                market.makeStatistics();
            }
        } catch (SecuritiesParsingException e) {
            logger.error("Couldn't update quotes, cause: " + e.getMessage(), e);
        }
        return this;
    }

}
