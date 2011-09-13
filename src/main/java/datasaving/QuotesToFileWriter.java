package datasaving;

import java.util.Map;
import java.util.Vector;

import stockwatch.securities.Security;
import stockwatch.stockmarkets.descriptions.WSEDescription.EWseMarketTypes;

public class QuotesToFileWriter implements QuotesWriter {

    private final static String outputStringFormat = "%20s";
    private DataStoreHolder dataStoreHolder;

    public QuotesToFileWriter(DataStoreHolder holder) {
        dataStoreHolder = holder;
    }

    public void write(Map<String, Vector<Security>> companies) {
        dataStoreHolder.writeQuotes(createTitle());
        dataStoreHolder.writeQuotes(createStockList(companies));
    }

    private String createTitle() {
        return String.format(outputStringFormat, "STOCK") + " "
                + String.format(outputStringFormat, "OPEN") + " "
                + String.format(outputStringFormat, "LAST TR. PRICE") + " "
                + String.format(outputStringFormat, "LAST TR. TIME") + " "
                + String.format(outputStringFormat, "CHANGE [%]") + "\n";
    }

    private String createStockList(Map<String, Vector<Security>> companies) {
        String stockList = "";

        // Iterate over all markets and write its quotes to stockList
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        for (EWseMarketTypes market : allMarkets) {
            stockList += market.name().toUpperCase() + "\n";
            for (Security company : companies.get(market.name())) {
                stockList += company.toString() + "\n";
            }
        }
        return stockList;
    }
}
