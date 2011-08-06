package stockwatch;

import java.util.Vector;

public class WarsawStockExchange implements StockExchange {

    private SessionStatistics sessionStatistics;
    private Vector<Company> companies;
    private StockExchangeDumper stockExchangeDumper;

    public WarsawStockExchange() {
        companies = new Vector<Company>();
        sessionStatistics = new SessionStatistics();
    }

    @Override
    public SessionStatistics makeSessionStatistics(boolean dumpResultToFile) {
        sessionStatistics.makeStatistics(companies);
        
        if (dumpResultToFile) {
            stockExchangeDumper = new StockExchangeDumper();
            stockExchangeDumper.dumpStatisticsToFile(sessionStatistics.toString());
        }
        
        return sessionStatistics;
    }

    @Override
    public void updateQuotes(Vector<Company> parsedCompanies, boolean dumpResultToFile) {
        companies = parsedCompanies;

        if (dumpResultToFile) {
            stockExchangeDumper = new StockExchangeDumper();
            stockExchangeDumper.dumpQuotesToFile(companies);
        }

    }

}
