package stockwatch.stockmarkets.descriptions;

public interface IMarketTypes {
    String getPageAddress();
    String[] getTags();
    String getName();
    IMarketTypes getType();
}
