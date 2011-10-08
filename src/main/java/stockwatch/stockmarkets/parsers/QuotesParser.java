package stockwatch.stockmarkets.parsers;

import java.util.Vector;

import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.stockmarkets.InternalMarket;

public interface QuotesParser {
    public Vector<InternalMarket> parseQuotes() throws SecuritiesParsingException;
    public void parseInfo();
}
