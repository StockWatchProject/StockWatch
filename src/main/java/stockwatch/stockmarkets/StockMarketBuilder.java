package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.securities.Security;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.descriptions.IMarketTypes;
import stockwatch.stockmarkets.parsers.QuotesParser;
import stockwatch.stockmarkets.parsers.WSEParser;


import com.google.common.annotations.VisibleForTesting;

import config.ConfigParser;
import datasaving.DataFileHolder;
import datasaving.DataStoreHolder;
import datasaving.QuotesToFileWriter;
import datasaving.QuotesWriter;
import datasaving.StatisticsToFileWriter;
import datasaving.StatisticsWriter;

public class StockMarketBuilder {
    private static StockMarketBuilder instance = new StockMarketBuilder();
    private ConfigParser configParser;
    
    private StockMarketBuilder() {
        configParser = new ConfigParser();
    }
    
    public static StockMarketBuilder getInstnce() {
        return instance;
    }

    @VisibleForTesting
    InternalMarkets initMarkets(IStockMarketDescription description) {
        InternalMarkets markets = new InternalMarkets();
        
        IMarketTypes allMarket[] = description.getInternalMarkets();
        for (IMarketTypes market : allMarket) {
            markets.getQuotes().put(market.getName(), new Vector<Security>());
            markets.getStatistics().put(market.getName(), new SessionStatistics());
        }
        return markets;
    }
    
    @VisibleForTesting
    QuotesWriter buildQuotesWriter(DataStoreHolder dataHolder) {
        QuotesWriter quotesWriter = new QuotesToFileWriter(dataHolder);
        return quotesWriter;
    }

    @VisibleForTesting
    StatisticsWriter buildStatisticsWriter(DataStoreHolder dataHolder) {
        StatisticsWriter statsWriters = new StatisticsToFileWriter(dataHolder);
        return statsWriters;
    }
    
    public StockMarket buildStockMarket(StockMarket stockmarket, IStockMarketDescription marketDesc) {
        InternalMarkets markets = initMarkets(marketDesc);
        //TODO: use self factoring reflection factory
        QuotesParser parser = new WSEParser(markets);
        
        //TODO: check this out
        Vector<InternalMarket> internalMarketCheck = new Vector<InternalMarket>();
        IMarketTypes allMarket[] = marketDesc.getInternalMarkets();
        for (IMarketTypes market : allMarket) {
            InternalMarket newInternalMarket = new InternalMarket(market.getType());
        }
        stockmarket.setInternalMarkets2(internalMarketCheck);
        
        stockmarket.setInternalMarkets(markets);
        stockmarket.setParser(parser);
        stockmarket.setName(marketDesc.getName());
        
        DataStoreHolder dataStoreHolder = new DataFileHolder(configParser.getDirectoryPath() + stockmarket.getName());
        stockmarket.setQuotestWriter(buildQuotesWriter(dataStoreHolder));
        stockmarket.setStatisticsWriter(buildStatisticsWriter(dataStoreHolder));
        
        return stockmarket;
    }
}
