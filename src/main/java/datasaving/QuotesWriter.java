package datasaving;

import java.util.Vector;

import stockwatch.stockmarkets.InternalMarket;


public interface QuotesWriter {
    public void write(Vector<InternalMarket> markets);
}
