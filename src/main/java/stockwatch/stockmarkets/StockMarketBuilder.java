package stockwatch.stockmarkets;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

import stockwatch.stockmarkets.descriptions.IMarketTypes;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;
import stockwatch.stockmarkets.parsers.QuotesParsersFactory;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Lists;

public class StockMarketBuilder {
    private static final Logger logger = Logger.getLogger(StockMarketBuilder.class);
    private static StockMarketBuilder instance = new StockMarketBuilder();
    private static final AtomicInteger idGen = new AtomicInteger();
    
    public static StockMarketBuilder getInstnce() {
        return instance;
    }

    @VisibleForTesting
    List<InternalMarket> initMarkets(IStockMarketDescription marketDesc) {
        List<InternalMarket> internalMarkets = Lists.newArrayList();
        
        IMarketTypes allMarket[] = marketDesc.getInternalMarkets();
        for (IMarketTypes market : allMarket) {
            InternalMarket newInternalMarket = new InternalMarket(market.getType(), idGen.getAndIncrement());
            internalMarkets.add(newInternalMarket);
            logger.debug("Added internal market: " +  market.getName());
        }
        return internalMarkets;
    }
    
    public StockMarket buildStockMarket(StockMarket stockmarket, IStockMarketDescription marketDesc) {
        stockmarket.setName(marketDesc.getName());
        
        List<InternalMarket> internalMarkets = initMarkets(marketDesc);
        stockmarket.setInternalMarkets(internalMarkets);
        
        QuotesParser parser = QuotesParsersFactory.getParser(marketDesc, internalMarkets);
        stockmarket.setParser(parser);
        
        logger.debug(stockmarket.getName().name() + " built succesfully.");
        return stockmarket;
    }
}
