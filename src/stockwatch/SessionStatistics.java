package stockwatch;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SessionStatistics {

    private final static int UNDEFINED_VALUE = -1;

    private int numberOfGrowingStocks;
    private int numberOfFallingStocks;
    private double avgReturn;

    private Vector<Security> sortedCompanies;
    private Vector<Security> topUp;
    private Vector<Security> topDown;
    private CompanyComparator companyComparator;

    public SessionStatistics() {
        topUp = new Vector<Security>();
        topDown = new Vector<Security>();
        sortedCompanies = new Vector<Security>();
        companyComparator = new CompanyComparator();

        numberOfGrowingStocks = 0;
        numberOfFallingStocks = 0;
    }

    private class CompanyComparator implements Comparator<Security> {

        @Override
        public int compare(Security o1, Security o2) {

            if (o1.getChange() < o2.getChange())
                return 1;
            else if (o1.getChange() > o2.getChange())
                return -1;
            else if (o1.getChange() == UNDEFINED_VALUE || o2.getChange() == UNDEFINED_VALUE)
                return 0;
            else
                return 0;
        }

    }

    private void CountGrowingCompanies(Vector<Security> companies) {
        numberOfGrowingStocks = 0;

        for (Security company : companies) {
            if (company.getChange() > 0) {
                ++numberOfGrowingStocks;
            }
        }
    }

    private void CountFallingCompanies(Vector<Security> companies) {
        numberOfFallingStocks = 0;

        for (Security company : companies) {
            if (company.getChange() < 0) {
                ++numberOfFallingStocks;
            }
        }
    }

    private void getTenTopStocks(Vector<Security> companies) {
        topUp.clear();

        try {
            for (int i = 0; i < 10; i++) {
                topUp.add(sortedCompanies.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

    }

    private void getTenTopBottomStocks(Vector<Security> companies) {
        topDown.clear();

        try {
            for (int i = companies.size() - 1; i > companies.size() - 11; i--) {
                topDown.add(sortedCompanies.get(i));
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    private void countAvgReturn(Vector<Security> companies) {
        int avg = 0;
        for (Security company : companies) {
            avg += company.getChange();
        }

        if (companies.size() != 0) {
            avgReturn = avg / companies.size();
        }
    }

    public void makeStatistics(Vector<Security> companies) {
        sortedCompanies.setSize(companies.size());
        Collections.copy(sortedCompanies, companies);
        Collections.sort(sortedCompanies, companyComparator);

        CountGrowingCompanies(sortedCompanies);
        CountFallingCompanies(sortedCompanies);
        getTenTopStocks(sortedCompanies);
        getTenTopBottomStocks(sortedCompanies);
        countAvgReturn(sortedCompanies);
    }

    public Vector<Security> getSortedCompanies() {
        return sortedCompanies;
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
