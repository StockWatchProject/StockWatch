package stockwatch.stockmarkets;

import java.util.Vector;

import stockwatch.securities.Security;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class InternalMarket {
    private IMarketTypes marketType;
    private Vector<Security> securities;
    private SessionStatistics stats;
    
    public InternalMarket(IMarketTypes type) {
        this.marketType = type;
        securities = new Vector<Security>();
        stats = new SessionStatistics();
    }
    
    public IMarketTypes getName() {
        return marketType;
    }
    
    public Vector<Security> getSecurities() {
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
}
