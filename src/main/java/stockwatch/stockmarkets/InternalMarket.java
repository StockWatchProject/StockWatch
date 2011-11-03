package stockwatch.stockmarkets;

import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.messages.QuoteMessages.Quote;
import stockwatch.messages.QuoteMessages.QuoteList;
import stockwatch.messages.QuoteMessages.QuoteList.Builder;
import stockwatch.securities.ISecurity;
import stockwatch.stockmarkets.descriptions.IMarketTypes;

import com.google.common.collect.ComparisonChain;

public class InternalMarket {
    private static final Logger logger = Logger.getLogger(InternalMarket.class);
    private IMarketTypes marketType;
    private Vector<ISecurity> securities;
    private SessionStatistics stats;
    private final int id;
    
    public int getId() {
        return id;
    }
    
    public InternalMarket(IMarketTypes type, int uniqueId) {
        this.id = uniqueId;
        this.marketType = type;
        securities = new Vector<ISecurity>();
        stats = new SessionStatistics();
    }
    
    private Quote getQuote(Quote quoteRequest) throws IllegalArgumentException {
        for (ISecurity security : securities) {
            if (ComparisonChain.start().compare(security.getSecurityName(), quoteRequest.getName())
                    .compare(security.getSecurityId(), quoteRequest.getId()).result() > 1) {
                logger.debug("Found security:" + security.getSecurityName());
                return security.fullSerialize();
            }
        }
        throw new IllegalArgumentException("No such security!");
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
    
    /* 
     * Serializes (all data) to QuoteListMsg requested securities from this.
     */
    public QuoteList getQuotes(List<Quote> quotesRequest) {
        Builder builder = QuoteList.newBuilder();
        
        for (Quote quoteReqest : quotesRequest) {
            try {
                builder.addQuote(this.getQuote(quoteReqest));
            } catch (IllegalArgumentException e) {
                logger.warn(e.getMessage());
            }
        }
        logger.debug("List of fully serialized: " + builder.getQuoteList().size() + " quotes from " + marketType + " will be sent.");
        return builder.build();
    }
    
    /* 
     * Serializes (only necessary data) to QuoteListMsg all available securities in this market.
     */
    public QuoteList getQuotes() {
        Builder builder = QuoteList.newBuilder();
        
        for (ISecurity security : securities) {
            builder.addQuote(security.simpleSerialize());
        }
        logger.debug("List of simply serialized: " + builder.getQuoteList().size() + " quotes from " + marketType + " will be sent.");
        return builder.build();
    }
    
    @Override
    public String toString() {
        StringBuffer strBuff = new StringBuffer();
        
        String stats = "";
        stats += this.getStats().toString();
        
        strBuff.append("\n");
        strBuff.append(this.getMarketType() + " quotes:\n");
        for (ISecurity security : securities) {
            strBuff.append(security.toString() + "\n");
        }
        strBuff.append(stats);
        return strBuff.toString();
    }
}
