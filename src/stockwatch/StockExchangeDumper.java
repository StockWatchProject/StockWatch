package stockwatch;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class StockExchangeDumper {

    private final static String outputStringFormat = "%15s";
    private final static String outputQuotesFile = "c:/quotes.txt";
    private final static String outputStatisticsFile = "c:/stats.txt";

    private FileWriter quotesFile;
    private FileWriter statisticsFile;

    private String title;

    public StockExchangeDumper() {
        title = String.format(outputStringFormat, "STOCK") + " " 
                + String.format(outputStringFormat, "OPEN") + " "
                + String.format(outputStringFormat, "LAST TR. PRICE") + " "
                + String.format(outputStringFormat, "LAST TR. TIME") + " "
                + String.format(outputStringFormat, "CHANGE [%]") + "\n";
    }

    void dumpQuotesToFile(Vector<Company> companies) {
        String stockList = "";

        for (Company company : companies) {
            stockList += company.toString() + "\n";
        }

        try {
            quotesFile = new FileWriter(outputQuotesFile);
            quotesFile.write(title);
            quotesFile.write(stockList);
            quotesFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    void dumpStatisticsToFile(String statistics) {
        try {
            statisticsFile = new FileWriter(outputStatisticsFile);
            statisticsFile.write(statistics);
            statisticsFile.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
