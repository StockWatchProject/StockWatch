package stockwatch;

public interface DataStoreHolder {
    public void writeQuotes(String value);
    public void writeStatistics(String value);
}
