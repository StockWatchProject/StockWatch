package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.QuotesWriter;
import stockwatch.StatisticsWriter;
import stockwatch.stockmarkets.parsers.QuotesParser;

public class StockMarket {

    private String name;
    private InternalMarkets internalMarkets;
    
    private Vector<QuotesWriter> quotesWriters;
    private Vector<StatisticsWriter> statisticsWriters;
    private QuotesParser quotesParser;
    
    public StockMarket(String name, QuotesParser quotesParser){
        this.quotesParser = quotesParser;
        this.name = name;
    }
    
    public String getName(){
    	return name;
    }
    
    private void saveQuotes() {
        for (QuotesWriter quotesWriter : quotesWriters) {
            quotesWriter.write(internalMarkets.getQuotes());
        }
    }
    
    private void saveStats() {
        for (StatisticsWriter statisticsWriter : statisticsWriters) {
            statisticsWriter.write(internalMarkets.getStatistics().toString());
        }
    }
    
    void setQuotestWriter(Vector<QuotesWriter> quotestWriter) {
        this.quotesWriters = quotestWriter;
    }

    void setStatisticsWriter(Vector<StatisticsWriter> statisticsWriter) {
        this.statisticsWriters = statisticsWriter;
    }
    
    public void save() {
        saveQuotes();
        saveStats();
    }

    public StockMarket updateQuotes() {
        internalMarkets = quotesParser.parseQuotes();
        return this;
    }

}
