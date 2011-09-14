package datasaving;

import java.util.Vector;

import stockwatch.securities.Security;
import stockwatch.stockmarkets.InternalMarket;

public class QuotesToFileWriter implements QuotesWriter {

    private final static String outputStringFormat = "%20s";
    private DataStoreHolder dataStoreHolder;

    public QuotesToFileWriter(DataStoreHolder holder) {
        dataStoreHolder = holder;
    }

    public void write(Vector<InternalMarket> internalMarkets) {
        dataStoreHolder.writeQuotes(createTitle());
        dataStoreHolder.writeQuotes(createStockList(internalMarkets));
    }

    private String createTitle() {
        return String.format(outputStringFormat, "STOCK") + " "
                + String.format(outputStringFormat, "OPEN") + " "
                + String.format(outputStringFormat, "LAST TR. PRICE") + " "
                + String.format(outputStringFormat, "LAST TR. TIME") + " "
                + String.format(outputStringFormat, "CHANGE [%]") + "\n";
    }

    private String createStockList(Vector<InternalMarket> internalMarkets) {
        String stockList = "";

        for (InternalMarket market : internalMarkets) {
            stockList += market.getName().toString() + "\n";
            for (Security company : market.getSecurities()) {
                stockList += company.toString() + "\n";
            }
        }
        return stockList;
    }
}
