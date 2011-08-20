package stockwatch;

public interface StockExchange {
    
    public abstract void makeSessionStatistics(boolean dumpResultToFile);
    public abstract void updateQuotes(InternalMarkets parsedSecurites, boolean dumpResultToFile);

}