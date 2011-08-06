package stockwatch;

import java.util.Vector;

public class WarsawStockExchange implements StockExchange {

    private SessionStatistics sessionStatistics;
    private Vector<Company> companies;
    
    public WarsawStockExchange() {
        companies = new Vector<Company>();
        sessionStatistics = new SessionStatistics();
    }
    
    @Override
    public SessionStatistics getSessionStatistics() {
        sessionStatistics.makeStatistics(companies);
        return sessionStatistics;
    }

    @Override
    public void updateQuotes(Vector<Company> parsedCompanies) {
        companies = parsedCompanies;
    }

}
