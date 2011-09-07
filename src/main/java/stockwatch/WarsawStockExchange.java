package stockwatch;

public class WarsawStockExchange implements StockExchange {

    private InternalMarkets wseInternalMarkets;
    
    private QuotesWriter quotestWriter;
    private StatisticsWriter statisticsWriter;
    private QuotesParser quotesParser;

    public WarsawStockExchange(ConfigParser parser) {
        wseInternalMarkets = new WseInternalMarkets();
        quotesParser = new WarsawStockExchangeParser();

        StockExchangeContextBuilder builder = new StockExchangeContextBuilder(parser);
        quotestWriter = builder.buildQuotesWriter();
        statisticsWriter = builder.buildStatisticsWriter();
    }
    
    @Override
    public void save() {
        quotestWriter.write(wseInternalMarkets.getQuotes());
        statisticsWriter.write(wseInternalMarkets.getStatistics().toString());
    }

    @Override
    public StockExchange updateQuotes() {
        wseInternalMarkets.updateMarkets(quotesParser.parseQuotes());
        return this;
    }

}
