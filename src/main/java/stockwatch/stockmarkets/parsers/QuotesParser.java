package stockwatch.stockmarkets.parsers;

import java.util.ArrayList;
import java.util.Vector;

import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.stockmarkets.InternalMarket;

public interface QuotesParser {
    public ArrayList<InternalMarket> parseQuotes() throws SecuritiesParsingException;
    public void parseInfo();
}
