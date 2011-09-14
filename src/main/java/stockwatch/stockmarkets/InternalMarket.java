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
    
    IMarketTypes getName() {
        return marketType;
    }
    
    void addSecurity(Security security) {
        securities.add(security);
    }
    
    void clearSecurities() {
        securities.clear();
    }
    
    void makeStatistics() {
        stats.makeStatistics(securities);
    }
}
