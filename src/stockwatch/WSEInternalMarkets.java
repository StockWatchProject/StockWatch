package stockwatch;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WSEInternalMarkets implements InternalMarkets {

    private Map<String, Vector<Security>> internalMarkets;
    private Map<String, SessionStatistics> marketsStatistics;

    public WSEInternalMarkets() {
        internalMarkets = new HashMap<String, Vector<Security>>();
        marketsStatistics = new HashMap<String, SessionStatistics>();
        
        WseMarketTypes allMarkets[] = WseMarketTypes.values();
        for (WseMarketTypes market : allMarkets) {
            internalMarkets.put(market.name(), new Vector<Security>());
            marketsStatistics.put(market.name(), new SessionStatistics());
        }
    }
    
    @Override
    public Map<String, Vector<Security>> getQuotes() {
        return Collections.unmodifiableMap(internalMarkets);
    }
    
    @Override
    public Map<String, SessionStatistics> getStatistics() {
        Collections.unmodifiableMap(marketsStatistics);
        return Collections.unmodifiableMap(marketsStatistics);
    }
    
    @Override
    public void updateMarkets(InternalMarkets updatedMarkets){
        this.internalMarkets = updatedMarkets.getQuotes();
        
        WseMarketTypes allMarkets[] = WseMarketTypes.values();
        for (WseMarketTypes market : allMarkets) {
            marketsStatistics.get(market.name()).makeStatistics(internalMarkets.get(market.name()));
        }
        
    }
}
