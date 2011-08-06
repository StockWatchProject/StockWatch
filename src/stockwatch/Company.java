package stockwatch;

import java.sql.Time;

public class Company {

    private final static String OUTPUT_STRING_FORMAT = "%15s";
    private final static int UNDEFINED_VALUE = -1;

    private String companyName;
    private int companyId;
    private double percentageChange;
    private double openPrice;
    private double lastTransactionPrice;
    private Time lastChanged;

    Company() {
        companyName = "";
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLastTransactionPrice(String closePrice) {
        lastTransactionPrice = 
            closePrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(closePrice.trim().replace(',', '.'));
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = 
            openPrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(openPrice.trim().replace(',','.'));
    }

    public void setPercentageChange(String percentageChange) {
        this.percentageChange = 
            percentageChange.equals("--") ? 0 : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    public void setCompanyId(String companyId) {
        this.companyId = 
            companyId.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(companyId.trim());
    }

    public void setLastChanged(String when) {
        when += ":00";
        try {
            this.lastChanged = Time.valueOf(when);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public int getCompanyId() {
        return companyId;
    }

    public double getChange() {
        return percentageChange;
    }

    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " "
                + String.format(OUTPUT_STRING_FORMAT, openPrice)+ " " 
                + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
                + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }

    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange) + "%";
    }

}
