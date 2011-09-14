package stockwatch.stockmarkets.parsers;

import stockwatch.stockmarkets.InternalMarkets;

public interface QuotesParser {
    public InternalMarkets parseQuotes();
    public void parseInfo();
}
