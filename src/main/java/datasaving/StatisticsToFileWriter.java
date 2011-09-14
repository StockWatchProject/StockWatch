package datasaving;

import java.util.Vector;

import stockwatch.stockmarkets.InternalMarket;

public class StatisticsToFileWriter implements StatisticsWriter {
    private DataStoreHolder dataFileHolder;

    public StatisticsToFileWriter(DataStoreHolder holder) {
        dataFileHolder = holder;
    }

    public void write(Vector<InternalMarket> internalMarkets) {
        String stats = "";
        for (InternalMarket market : internalMarkets) {
            stats += market.getName().toString() + "\n";
            stats += market.getStats().toString() + "\n";
        }
        dataFileHolder.writeStatistics(stats);
    }
}
