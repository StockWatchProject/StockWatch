package stockwatch.stockmarkets.parsers;

import java.util.List;

import stockwatch.exceptions.SecuritiesParsingException;
import stockwatch.stockmarkets.InternalMarket;

public interface QuotesParser {
    public List<InternalMarket> parseQuotes() throws SecuritiesParsingException;
    public void parseInfo();
}
