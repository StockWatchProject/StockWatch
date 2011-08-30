package stockwatch;

import java.io.FileWriter;
import java.io.IOException;

public class DataFileHolder implements DataStoreHolder {

    private String quotesStorePath;
    private String statisticsStorePath;

    public DataFileHolder(String quotesFilePath, String statistiscFilePath) {
        quotesStorePath = new String(quotesFilePath);
        statisticsStorePath = new String(statistiscFilePath);
    }

    public void writeQuotes(String value) {
        try {
            FileWriter quotesStore = new FileWriter(quotesStorePath);
            quotesStore.write(value);
            quotesStore.close();
        } catch (IOException x) {
            x.getStackTrace();
            System.out.println(x.getMessage());
        }
    }

    public void writeStatistics(String value) {
        try {
            FileWriter statisticsStore = new FileWriter(statisticsStorePath);
            statisticsStore.write(value);
            statisticsStore.close();
        } catch (IOException x) {
            x.getStackTrace();
            System.out.println(x.getMessage());
        }
    }
}
