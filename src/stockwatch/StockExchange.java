package stockwatch;

public interface StockExchange {
    
    public abstract void updateQuotes(InternalMarkets parsedSecurites, boolean dumpResultToFile);

}