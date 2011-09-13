package stockwatch.stockmarkets;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import stockwatch.SessionStatistics;
import stockwatch.securities.Security;
import stockwatch.stockmarkets.descriptions.WSEDescription.EWseMarketTypes;

public class InternalMarkets{

    private Map<String, Vector<Security>> internalMarkets;
    private Map<String, SessionStatistics> marketsStatistics;

    public InternalMarkets() {
        internalMarkets = new HashMap<String, Vector<Security>>();
        marketsStatistics = new HashMap<String, SessionStatistics>();
        
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        for (EWseMarketTypes market : allMarkets) {
            internalMarkets.put(market.name(), new Vector<Security>());
            marketsStatistics.put(market.name(), new SessionStatistics());
        }
    }
    
    public Map<String, Vector<Security>> getQuotes() {
        return internalMarkets;
    }
    
    
    public Map<String, SessionStatistics> getStatistics() {
        return marketsStatistics;
    }
    
    public void makeStatistics(){
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        for (EWseMarketTypes market : allMarkets) {
            marketsStatistics.get(market.name()).makeStatistics(internalMarkets.get(market.name()));
        }
        
    }
}
