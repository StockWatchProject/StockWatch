package stockwatch;

import java.sql.Time;

public class Shares implements Security {

    private final static String OUTPUT_STRING_FORMAT = "%15s";
    private final static int UNDEFINED_VALUE = -1;

    private String companyName;
    private int companyId;
    private double percentageChange;
    private double openPrice;
    private double lastTransactionPrice;
    private Time lastChanged;

    public Shares() {
        companyName = "";
    }

    public Shares(String companyName, 
                   int companyId, 
                   double change, 
                   double openPrice, 
                   double lastPrice,
                   Time lastChanged) {
        this.companyName = companyName;
        this.companyId = companyId;
        this.percentageChange = change;
        this.openPrice = openPrice;
        this.lastTransactionPrice = lastPrice;
        this.lastChanged = lastChanged; 
    }

    @Override
    public void setSecurityName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public void setLastTransactionPrice(String closePrice) {
        lastTransactionPrice = 
            closePrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(closePrice.trim().replace(',', '.'));
    }

    @Override
    public void setOpenPrice(String openPrice) {
        this.openPrice = 
            openPrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(openPrice.trim().replace(',','.'));
    }

    @Override
    public void setPercentageChange(String percentageChange) {
        this.percentageChange = 
            percentageChange.equals("--") ? 0 : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    @Override
    public void setSecurityId(String companyId) {
        this.companyId = 
            companyId.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(companyId.trim());
    }

    @Override
    public void setLastChangedTime(String when) {
        when += ":00";
        try {
            this.lastChanged = Time.valueOf(when);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getSecurityId() {
        return companyId;
    }

    @Override
    public double getChange() {
        return percentageChange;
    }

    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " " 
            + String.format(OUTPUT_STRING_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
            + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }

    @Override
    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " "
            + String.format(OUTPUT_STRING_FORMAT, percentageChange) + "%";
    }

}
