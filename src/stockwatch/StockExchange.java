package stockwatch;

import java.util.Vector;

public interface StockExchange {
    
    SessionStatistics getSessionStatistics();
    void updateQuotes(Vector<Company> parsedCompanies);

}
