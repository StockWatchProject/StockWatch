package stockwatch.stockmarkets.parsers;

import java.util.Vector;

import stockwatch.stockmarkets.InternalMarket;

public interface QuotesParser {
    public Vector<InternalMarket> parseQuotes();
    public void parseInfo();
}
