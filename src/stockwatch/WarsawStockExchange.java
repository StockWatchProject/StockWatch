package stockwatch;

public class WarsawStockExchange implements StockExchange {

    private InternalMarkets wseInternalMarkets;
    private StockExchangeDumper stockExchangeDumper;

    public WarsawStockExchange(ConfigParser parser) {
        wseInternalMarkets = new WSEInternalMarkets();
        stockExchangeDumper = new StockExchangeDumper(parser);
    }

    @Override
    public void updateQuotes(InternalMarkets parsedSecurites, boolean dumpResultToFile) {
        wseInternalMarkets.updateMarkets(parsedSecurites);

        if (dumpResultToFile) {
            stockExchangeDumper.dumpQuotesToFile(wseInternalMarkets.getQuotes());
            stockExchangeDumper.dumpStatisticsToFile(wseInternalMarkets.getStatistics().toString());
        }

    }

}
