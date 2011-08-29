package stockwatch;

public class StatisticsToFileWriter implements StatisticsWriter {
    private DataStoreHolder dataFileHolder;

    public StatisticsToFileWriter(DataStoreHolder holder) {
        dataFileHolder = holder;
    }

    public void write(String statistics) {
        dataFileHolder.writeStatistics(statistics);
    }
}
