package stockwatch.stockmarkets.descriptions;

import stockwatch.securities.SecurityTypes;

public interface IMarketTypes {
    String getPageAddress();
    String[] getTags();
    public SecurityTypes getSecurityType();
    String getName();
    IMarketTypes getType();
}
