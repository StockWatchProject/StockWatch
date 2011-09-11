package stockwatch;

import java.util.Vector;

public class StockExchange {

    private String name;
    private InternalMarkets internalMarkets;
    
    private Vector<QuotesWriter> quotesWriters;
    private Vector<StatisticsWriter> statisticsWriters;
    private QuotesParser quotesParser;
    
    public StockExchange(String name, QuotesParser quotesParser){
        internalMarkets = new InternalMarkets();
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

    public StockExchange updateQuotes() {
        internalMarkets.updateMarkets(quotesParser.parseQuotes());
        return this;
    }

}
