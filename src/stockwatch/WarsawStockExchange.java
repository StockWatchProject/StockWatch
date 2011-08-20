package stockwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WarsawStockExchange implements StockExchange {

    private SessionStatistics sessionStatistics;
    private Map<String, Vector<Company>> companies;
    private StockExchangeDumper stockExchangeDumper;

    public WarsawStockExchange(ConfigParser parser) {
        companies = new HashMap<String, Vector<Company>>();
        sessionStatistics = new SessionStatistics();
        stockExchangeDumper = new StockExchangeDumper(parser);
    }

    @Override
    public void makeSessionStatistics(boolean dumpResultToFile) {
        WseMarketTypes allMarkets[] = WseMarketTypes.values();
        for (WseMarketTypes market : allMarkets) {
            sessionStatistics.makeStatistics(companies.get(market.name()));

            if (dumpResultToFile) {
                stockExchangeDumper.dumpStatisticsToFile(sessionStatistics.toString());
            }
        }
    }

    @Override
    public void updateQuotes(Map<String, Vector<Company>> parsedCompanies, boolean dumpResultToFile) {
        companies = parsedCompanies;

        if (dumpResultToFile) {
            stockExchangeDumper.dumpQuotesToFile(companies);
        }

    }

}
