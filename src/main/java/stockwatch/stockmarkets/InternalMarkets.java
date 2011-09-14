package stockwatch.stockmarkets;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import stockwatch.securities.Security;
import stockwatch.stockmarkets.descriptions.WSEDescription.MarketTypes;

public class InternalMarkets{

    private Map<String, Vector<Security>> internalMarkets;
    private Map<String, SessionStatistics> marketsStatistics;

    public InternalMarkets() {
        internalMarkets = new HashMap<String, Vector<Security>>();
        marketsStatistics = new HashMap<String, SessionStatistics>();
    }
    
    public Map<String, Vector<Security>> getQuotes() {
        return internalMarkets;
    }

    public Map<String, SessionStatistics> getStatistics() {
        return marketsStatistics;
    }
    
    public void makeStatistics(){
        MarketTypes allMarkets[] = MarketTypes.values();
        for (MarketTypes market : allMarkets) {
            marketsStatistics.get(market.name()).makeStatistics(internalMarkets.get(market.name()));
        }
        
    }
}
