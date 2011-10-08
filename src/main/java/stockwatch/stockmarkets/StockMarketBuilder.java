package stockwatch.stockmarkets;

import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.stockmarkets.descriptions.IMarketTypes;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;
import stockwatch.stockmarkets.parsers.QuotesParsersFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;

public class StockMarketBuilder {
    private static final Logger logger = Logger.getLogger(StockMarketBuilder.class);
    private static StockMarketBuilder instance = new StockMarketBuilder();
    
    public static StockMarketBuilder getInstnce() {
        return instance;
    }

    @VisibleForTesting
    Vector<InternalMarket> initMarkets(IStockMarketDescription marketDesc) {
        Vector<InternalMarket> internalMarkets = new Vector<InternalMarket>();
        
        IMarketTypes allMarket[] = marketDesc.getInternalMarkets();
        for (IMarketTypes market : allMarket) {
            InternalMarket newInternalMarket = new InternalMarket(market.getType());
            internalMarkets.add(newInternalMarket);
            logger.debug("Added internal market: " +  market.getName());
        }
        return internalMarkets;
    }
    
    public StockMarket buildStockMarket(StockMarket stockmarket, IStockMarketDescription marketDesc) {
        stockmarket.setName(marketDesc.getName());
        
        Vector<InternalMarket> internalMarkets = initMarkets(marketDesc);
        stockmarket.setInternalMarkets(internalMarkets);
        
        QuotesParser parser = QuotesParsersFactory.getParser(marketDesc, internalMarkets);
        Preconditions.checkNotNull(parser);
        stockmarket.setParser(parser);
        
        logger.debug(stockmarket.getName().name() + " built succesfully.");
        return stockmarket;
    }
}
