package stockwatch;

import java.util.Map;
import java.util.Vector;

public interface StockExchange {
    
    void makeSessionStatistics(boolean dumpResultToFile);
    void updateQuotes(Map<String, Vector<Company>> parsedCompanies, boolean dumpResultToFile);

}