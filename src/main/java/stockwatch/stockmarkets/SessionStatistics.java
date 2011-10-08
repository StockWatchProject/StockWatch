package stockwatch.stockmarkets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.Utils;
import stockwatch.securities.Security;

public class SessionStatistics {
    private static final Logger logger = Logger.getLogger(SessionStatistics.class);
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
        numberOfGrowingStocks = Utils.countIf(companies, Utils.isUp);
    }

    private void CountFallingCompanies(Vector<Security> companies) {
        numberOfFallingStocks = Utils.countIf(companies, Utils.isDown);
    }

    private void getTenTopStocks() {
        try {
            if (sortedSecurities.size() >= NUMBER_OF_TOP_STOCKS) {
                topUp = sortedSecurities.subList(0, NUMBER_OF_TOP_STOCKS);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }

    }

    private void getTenTopBottomStocks() {
        try {
            topDown = sortedSecurities.subList(sortedSecurities.size()
                    - NUMBER_OF_TOP_STOCKS, sortedSecurities.size());
            Collections.reverse(topDown);
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
    }

    private void countAvgReturn(Vector<Security> companies) {
        double avg = 0;
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

        stats += "Amount of growing securities: " + numberOfGrowingStocks + "\n";
        stats += "Amount of falling securities: " + numberOfFallingStocks + "\n";

        stats += "\nTop ten growing securities: \n\n";
        for (Security company : topUp) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nTop ten falling securities: \n\n";
        for (Security company : topDown) {
            stats += company.sessionResult() + "\n";
        }

        stats += "\nAverage return of securities: " + avgReturn + " [%]" + "\n";

        return stats;
    }

}
