package stockwatch;

public class WarsawStockExchange implements StockExchange {

    private InternalMarkets wseInternalMarkets;
    private QuotesWriter quotestWriter;
    private StatisticsWriter statisticsWriter;

    public WarsawStockExchange(ConfigParser parser) {
        wseInternalMarkets = new WseInternalMarkets();

        StockExchangeContextBuilder builder = new StockExchangeContextBuilder(parser);
        quotestWriter = builder.buildQuotesWriter();
        statisticsWriter = builder.buildStatisticsWriter();
    }

    @Override
    public void updateQuotes(InternalMarkets parsedSecurites, boolean dumpResultToFile) {
        wseInternalMarkets.updateMarkets(parsedSecurites);

        if (dumpResultToFile) {
            quotestWriter.write(wseInternalMarkets.getQuotes());
            statisticsWriter.write(wseInternalMarkets.getStatistics().toString());
        }

    }

}
