package stockwatch.stockmarkets;

import java.util.Vector;


import com.google.common.annotations.VisibleForTesting;

import config.ConfigParser;
import datasaving.DataFileHolder;
import datasaving.DataStoreHolder;
import datasaving.QuotesToFileWriter;
import datasaving.QuotesWriter;
import datasaving.StatisticsToFileWriter;
import datasaving.StatisticsWriter;

public class StockMarketBuilder {
    private ConfigParser configParser;
    
    StockMarketBuilder() {
        configParser = new ConfigParser();
    }

    public StockMarket buildStockMarket(StockMarket stockmarket) {
        DataStoreHolder dataStoreHolder = 
                new DataFileHolder(configParser.getDirectoryPath() + stockmarket.getName());
        
        stockmarket.setQuotestWriter(buildQuotesWriter(dataStoreHolder));
        stockmarket.setStatisticsWriter(buildStatisticsWriter(dataStoreHolder));
        
        return stockmarket;
    }
    
    @VisibleForTesting
    Vector<QuotesWriter> buildQuotesWriter(DataStoreHolder dataHolder) {
        Vector<QuotesWriter> quotesWriters = new Vector<QuotesWriter>();
        if (configParser.dumpToFile()) 
            quotesWriters.add(new QuotesToFileWriter(dataHolder));
        if (configParser.dumpToDatabase());
            // add quotes to database writer 
        return quotesWriters;
    }

    @VisibleForTesting
    Vector<StatisticsWriter> buildStatisticsWriter(DataStoreHolder dataHolder) {
        Vector<StatisticsWriter> statsWriters = new Vector<StatisticsWriter>();
        if (configParser.dumpToFile()) 
            statsWriters.add(new StatisticsToFileWriter(dataHolder));
        if (configParser.dumpToDatabase());
            // add stats to database writer
        return statsWriters;
    }
}
