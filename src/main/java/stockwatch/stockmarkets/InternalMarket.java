package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.securities.ISecurity;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class InternalMarket {
    private IMarketTypes marketType;
    private Vector<ISecurity> securities;
    private SessionStatistics stats;
    
    public InternalMarket(IMarketTypes type) {
        this.marketType = type;
        securities = new Vector<ISecurity>();
        stats = new SessionStatistics();
    }
    
    public IMarketTypes getMarketType() {
        return marketType;
    }
    
    public Vector<ISecurity> getSecurities() {
        return securities;
    }
    
    public SessionStatistics getStats() {
        return stats;
    }
    
    public void clearSecurities() {
        securities.clear();
    }
    
    public void makeStatistics() {
        stats.makeStatistics(securities);
    }

    @Override
    public String toString() {
        String stats = "";
        stats += this.getStats().toString();
        
        String stockList = "\n";
        stockList += this.getMarketType() + " quotes:\n";
        for (ISecurity security : securities) {
            stockList += security.toString() + "\n";
        }
        
        return stockList + stats;
    }
    
}
