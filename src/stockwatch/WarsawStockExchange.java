package stockwatch;

public class WarsawStockExchange implements StockExchange {

    private SessionStatistics sessionStatistics;
    private InternalMarkets wseInternalMarkets;
    private StockExchangeDumper stockExchangeDumper;

    public WarsawStockExchange(ConfigParser parser) {
        wseInternalMarkets = new WSEInternalMarkets();
        sessionStatistics = new SessionStatistics();
        stockExchangeDumper = new StockExchangeDumper(parser);
    }

    @Override
    public void makeSessionStatistics(boolean dumpResultToFile) {
        WseMarketTypes allMarkets[] = WseMarketTypes.values();
        for (WseMarketTypes market : allMarkets) {
            sessionStatistics.makeStatistics(wseInternalMarkets.getQuotes().get(market.name()));

            if (dumpResultToFile) {
                stockExchangeDumper.dumpStatisticsToFile(sessionStatistics.toString());
            }
        }
    }

    @Override
    public void updateQuotes(InternalMarkets parsedSecurites, boolean dumpResultToFile) {
        wseInternalMarkets = parsedSecurites;

        if (dumpResultToFile) {
            stockExchangeDumper.dumpQuotesToFile(wseInternalMarkets.getQuotes());
        }

    }

}
