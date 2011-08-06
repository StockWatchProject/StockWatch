package stockwatch;

import java.util.Vector;

public interface StockExchange {
    
    SessionStatistics makeSessionStatistics(boolean dumpResultToFile);
    void updateQuotes(Vector<Company> parsedCompanies, boolean dumpResultToFile);

}
