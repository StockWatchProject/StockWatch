package stockwatch.stockmarkets.parsers;

import stockwatch.stockmarkets.InternalMarkets;


public interface QuotesParser {
    
    public abstract InternalMarkets parseQuotes();
    public abstract void parseInfo();

}
