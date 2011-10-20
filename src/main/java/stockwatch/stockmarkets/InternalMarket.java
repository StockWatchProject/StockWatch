package stockwatch.stockmarkets;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.messages.QuoteMessages.Quote;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.securities.ISecurity;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class InternalMarket {
    private static final Logger logger = Logger.getLogger(InternalMarket.class);
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
    
    public QuoteList serialize() {
        List<Quote> quotes = new ArrayList<Quote>(securities.size());
        for (ISecurity s : securities) {
            Quote q = Quote.newBuilder()
                    .setName(s.getSecurityName())
                    .setId(s.getSecurityId())
                    .setLastPrice(s.getLastTransactionPrice())
                    .build();
            
            quotes.add(q);
        }
        logger.debug(quotes.toString());
        return QuoteList.newBuilder().addAllQuote(quotes).build();
    }
    
}
