package stockwatch;

import java.util.Vector;

public class WarsawStockExchange implements StockExchange {

    private InternalMarkets wseInternalMarkets;
    
    private Vector<QuotesWriter> quotesWriters;
    private Vector<StatisticsWriter> statisticsWriters;
    private QuotesParser quotesParser;

    public WarsawStockExchange() {
        wseInternalMarkets = new WseInternalMarkets();
        quotesParser = new WarsawStockExchangeParser();
    }
    
    private void saveQuotes() {
        for (QuotesWriter quotesWriter : quotesWriters) {
            quotesWriter.write(wseInternalMarkets.getQuotes());
        }
    }
    
    private void saveStats() {
        for (StatisticsWriter statisticsWriter : statisticsWriters) {
            statisticsWriter.write(wseInternalMarkets.getStatistics().toString());
        }
    }
    
    void setQuotestWriter(Vector<QuotesWriter> quotestWriter) {
        this.quotesWriters = quotestWriter;
    }

    void setStatisticsWriter(Vector<StatisticsWriter> statisticsWriter) {
        this.statisticsWriters = statisticsWriter;
    }
    
    @Override
    public void save() {
        saveQuotes();
        saveStats();
    }

    @Override
    public StockExchange updateQuotes() {
        wseInternalMarkets.updateMarkets(quotesParser.parseQuotes());
        return this;
    }

}
