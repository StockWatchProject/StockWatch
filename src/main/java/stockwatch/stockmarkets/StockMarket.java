package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;
import datasaving.QuotesWriter;
import datasaving.StatisticsWriter;

public class StockMarket {

    private MarketNames name;
    private InternalMarkets internalMarkets;
    
    private Vector<InternalMarket> internalMarkets2;
    
    private QuotesWriter quotesWriter;
    private StatisticsWriter statisticsWriter;
    private QuotesParser quotesParser;
    
    public StockMarket(IStockMarketDescription description){
        StockMarketBuilder.getInstnce().buildStockMarket(this, description);
    }
    
    void setName(MarketNames name) {
        this.name = name;
    }
    
    void setQuotestWriter(QuotesWriter quotestWriter) {
        this.quotesWriter = quotestWriter;
    }

    void setStatisticsWriter(StatisticsWriter statisticsWriter) {
        this.statisticsWriter = statisticsWriter;
    }
    
    void setParser(QuotesParser parser) {
        this.quotesParser = parser;
    }
    
    void setInternalMarkets(InternalMarkets markets) {
        this.internalMarkets = markets;
    }
    
    void setInternalMarkets2(Vector<InternalMarket> internal) {
        internalMarkets2 = internal;
    }
    
    public MarketNames getName(){
    	return name;
    }
    
    public void save() {
        quotesWriter.write(internalMarkets.getQuotes());
        statisticsWriter.write(internalMarkets.getStatistics().toString());
    }

    public StockMarket updateQuotes() {
        internalMarkets = quotesParser.parseQuotes();
        return this;
    }

}
