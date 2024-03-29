package stockwatch.stockmarkets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.Utils;
import stockwatch.securities.ISecurity;

public class SessionStatistics {
    private static final Logger logger = Logger.getLogger(SessionStatistics.class);
    private final static int NUMBER_OF_TOP_STOCKS = 10;

    private int numberOfGrowingStocks;
    private int numberOfFallingStocks;
    private double avgReturn;

    private Vector<ISecurity> sortedSecurities;
    private List<ISecurity> topUp;
    private List<ISecurity> topDown;

    public SessionStatistics() {
        topUp = new ArrayList<ISecurity>();
        topDown = new ArrayList<ISecurity>();
        sortedSecurities = new Vector<ISecurity>();
    }

    private void CountGrowingCompanies(Vector<ISecurity> companies) {
        numberOfGrowingStocks = Utils.countIf(companies, Utils.isUp);
    }

    private void CountFallingCompanies(Vector<ISecurity> companies) {
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
            if (sortedSecurities.size() >= NUMBER_OF_TOP_STOCKS) {
                topDown = sortedSecurities.subList(sortedSecurities.size()
                        - NUMBER_OF_TOP_STOCKS, sortedSecurities.size());
                Collections.reverse(topDown);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        }
    }

    private void countAvgReturn(Vector<ISecurity> companies) {
        double avg = 0;
        if (companies.size() == 0) {
            return;
        }
        for (ISecurity company : companies) {
            avg += company.getPercentageChange();
        }
        avgReturn = avg / companies.size();
    }

    public void makeStatistics(Vector<ISecurity> companies) {
        sortedSecurities.clear();
        sortedSecurities.setSize(companies.size());
        Collections.copy(sortedSecurities, companies);
        Collections.sort(sortedSecurities, new Utils.CompanyComparator());

        CountGrowingCompanies(sortedSecurities);
        CountFallingCompanies(sortedSecurities);
        getTenTopStocks();
        getTenTopBottomStocks();
        countAvgReturn(sortedSecurities);
    }

    public Vector<ISecurity> getSortedCompanies() {
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
        StringBuffer strBuff = new StringBuffer();
        
        strBuff.append("\n");

        strBuff.append("Amount of growing securities: " + this.numberOfGrowingStocks + "\n");
        strBuff.append("Amount of falling securities: " + this.numberOfFallingStocks + "\n");

        strBuff.append("\nTop ten growing securities: \n\n");
        for (ISecurity company : this.topUp) {
            strBuff.append(company.sessionResult() + "\n");
        }

        strBuff.append("\nTop ten falling securities: \n\n");
        for (ISecurity company : this.topDown) {
            strBuff.append(company.sessionResult() + "\n");
        }

        strBuff.append("\nAverage return of securities: " + this.avgReturn + " [%]" + "\n");

        return strBuff.toString();
    }

}
