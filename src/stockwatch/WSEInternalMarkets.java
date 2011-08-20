package stockwatch;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class WSEInternalMarkets implements InternalMarkets {

    private Map<String, Vector<Security>> internalMarkets;

    public WSEInternalMarkets() {
        internalMarkets = new HashMap<String, Vector<Security>>();
        WseMarketTypes allMarkets[] = WseMarketTypes.values();
        for (WseMarketTypes market : allMarkets) {
            internalMarkets.put(market.name(), new Vector<Security>());
        }
    }

    @Override
    public Map<String, Vector<Security>> getQuotes() {
        return internalMarkets;
    }

}
