package stockwatch;

public interface StockExchange {
    
    public abstract StockExchange updateQuotes();
    public abstract void save(); 

}