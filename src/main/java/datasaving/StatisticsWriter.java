package datasaving;

import java.util.Vector;

import stockwatch.stockmarkets.InternalMarket;

public interface StatisticsWriter {
    public void write(Vector<InternalMarket> internalMarkets);
}
