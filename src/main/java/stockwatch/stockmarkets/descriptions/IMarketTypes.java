package stockwatch.stockmarkets.descriptions;

import stockwatch.securities.SecurityTypes;

public interface IMarketTypes {
    public String getPageAddress();
    public String[] getTags();
    public SecurityTypes getSecurityType();
    public String getName();
    public IMarketTypes getType();
}
