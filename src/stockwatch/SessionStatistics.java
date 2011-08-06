package stockwatch;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

public class SessionStatistics {

    private final static int UNDEFINED_VALUE = 9999;

    private int numberOfGrowingStocks;
    private int numberOfFallingStocks;
    private double avgReturn;

    private Vector<Company> sortedCompanies;
    private Vector<Company> topUp;
    private Vector<Company> topDown;
    
    private CompanyComparator companyComparator;

    private class CompanyComparator implements Comparator<Company> {

        @Override
        public int compare(Company o1, Company o2) {

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

    SessionStatistics() {
        topUp = new Vector<Company>();
        topDown = new Vector<Company>();
        sortedCompanies = new Vector<Company>();
        companyComparator = new CompanyComparator();

        numberOfGrowingStocks = 0;
        numberOfFallingStocks = 0;
    }

    private void CountGrowingCompanies(Vector<Company> companies) {
        numberOfGrowingStocks = 0;

        for (Company company : companies) {
            if (company.getChange() > 0) {
                ++numberOfGrowingStocks;
            }
        }
    }

    private void CountFallingCompanies(Vector<Company> companies) {
        numberOfFallingStocks = 0;

        for (Company company : companies) {
            if (company.getChange() < 0) {
                ++numberOfFallingStocks;
            }
        }
    }

    private void getTenTopStocks(Vector<Company> companies) {
        topUp.clear();

        for (int i = 0; i < 10; i++) {
            topUp.add(sortedCompanies.get(i));
        }

    }

    private void getTenTopBottomStocks(Vector<Company> companies) {
        topDown.clear();

        for (int i = companies.size() - 1; i > companies.size() - 11; i--) {
            topDown.add(sortedCompanies.get(i));
        }
    }

    private void countAvgReturn(Vector<Company> companies) {
        int avg = 0;
        for (Company company : companies) {
            avg += company.getChange();
        }

        avgReturn = avg / companies.size();
    }

    public void makeStatistics(Vector<Company> companies) {
        sortedCompanies.setSize(companies.size());
        Collections.copy(sortedCompanies, companies);
        Collections.sort(sortedCompanies, companyComparator);

        CountGrowingCompanies(sortedCompanies);
        CountFallingCompanies(sortedCompanies);
        getTenTopStocks(sortedCompanies);
        getTenTopBottomStocks(sortedCompanies);
        countAvgReturn(sortedCompanies);
    }

    @Override
    public String toString() {

        String stats = "\n";

        stats += "Amount of growing stocks: " + numberOfGrowingStocks + "\n";
        stats += "Amount of falling stocks: " + numberOfFallingStocks + "\n";

        stats += "\nTop ten growing stocks: \n\n";
        for (Company company : topUp) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nTop ten falling stocks: \n\n";
        for (Company company : topDown) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nAverage return of stocks: " + avgReturn + " [%]";

        return stats;
    }

}
