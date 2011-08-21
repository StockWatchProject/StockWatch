package stockwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class SessionStatistics {

    private final static int NUMBER_OF_TOP_STOCKS = 10;

    private int numberOfGrowingStocks;
    private int numberOfFallingStocks;
    private double avgReturn;

    private Vector<Security> sortedSecurities;
    private List<Security> topUp;
    private List<Security> topDown;

    public SessionStatistics() {
        topUp = new ArrayList<Security>();
        topDown = new ArrayList<Security>();
        sortedSecurities = new Vector<Security>();

        numberOfGrowingStocks = 0;
        numberOfFallingStocks = 0;
    }

    private void CountGrowingCompanies(Vector<Security> companies) {
        numberOfGrowingStocks = Utils.countIf(companies, new Utils.Up());
    }

    private void CountFallingCompanies(Vector<Security> companies) {
        numberOfFallingStocks = Utils.countIf(companies, new Utils.Down());
    }

    private void getTenTopStocks() {
        try {
            topUp = sortedSecurities.subList(0, NUMBER_OF_TOP_STOCKS);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private void getTenTopBottomStocks() {
        try {
            topDown = sortedSecurities.subList(sortedSecurities.size() - NUMBER_OF_TOP_STOCKS, sortedSecurities.size());
            Collections.reverse(topDown);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void countAvgReturn(Vector<Security> companies) {
        int avg = 0;
        if (companies.size() == 0)
            return;

        for (Security company : companies) {
            avg += company.getChange();
        }
        avgReturn = avg / companies.size();
    }

    public void makeStatistics(Vector<Security> companies) {
        sortedSecurities.setSize(companies.size());
        Collections.copy(sortedSecurities, companies);
        Collections.sort(sortedSecurities, new Utils.CompanyComparator());

        CountGrowingCompanies(sortedSecurities);
        CountFallingCompanies(sortedSecurities);
        getTenTopStocks();
        getTenTopBottomStocks();
        countAvgReturn(sortedSecurities);
    }

    public Vector<Security> getSortedCompanies() {
        return sortedSecurities;
    }

    public int getNumberOfGrowingStocks() {
        return numberOfGrowingStocks;
    }

    public int getNumberOfFallingStocks() {
        return numberOfFallingStocks;
    }

    @Override
    public String toString() {

        String stats = "\n";

        stats += "Amount of growing stocks: " + numberOfGrowingStocks + "\n";
        stats += "Amount of falling stocks: " + numberOfFallingStocks + "\n";

        stats += "\nTop ten growing stocks: \n\n";
        for (Security company : topUp) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nTop ten falling stocks: \n\n";
        for (Security company : topDown) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nAverage return of stocks: " + avgReturn + " [%]";

        return stats;
    }

}
