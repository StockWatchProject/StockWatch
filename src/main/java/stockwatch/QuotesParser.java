package stockwatch;


public interface QuotesParser {
    
    public abstract InternalMarkets parseQuotes();
    public abstract void parseInfo();

}
