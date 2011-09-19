package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.stockmarkets.descriptions.DescriptionsFactory;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;
import stockwatch.stockmarkets.parsers.QuotesParser;
import datasaving.QuotesWriter;
import datasaving.StatisticsWriter;

public class StockMarket {

    private MarketNames name;
    private Vector<InternalMarket> internalMarkets;
    private QuotesWriter quotesWriter;
    private StatisticsWriter statisticsWriter;
    private QuotesParser quotesParser;
    
    public StockMarket(MarketNames mName){
        IStockMarketDescription description = DescriptionsFactory.getDescription(mName);
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
    
    void setInternalMarkets(Vector<InternalMarket> internalMarkets) {
        this.internalMarkets = internalMarkets;
    }
    
    public MarketNames getName(){
    	return name;
    }
    
    public void save() {
        quotesWriter.write(internalMarkets);
        statisticsWriter.write(internalMarkets);
    }

    public StockMarket updateQuotes() {
        internalMarkets = quotesParser.parseQuotes();
        return this;
    }

}
