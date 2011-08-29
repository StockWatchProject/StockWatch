package stockwatch;

public class StockExchangeContextBuilder {
    private DataStoreHolder dataStoreHolder;

    StockExchangeContextBuilder(ConfigParser parser) {
        dataStoreHolder = new DataFileHolder(parser.getQuotesDataFilePath(),
                                             parser.getStatisticsDataFilePath());
    }

    public QuotesWriter buildQuotesWriter() {
        return new QuotesToFileWriter(dataStoreHolder);
    }

    public StatisticsWriter buildStatisticsWriter() {
        return new StatisticsToFileWriter(dataStoreHolder);
    }
}
