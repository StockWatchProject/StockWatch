package stockwatch.securities;

import java.sql.Time;


public class Bond implements Security {

    String bondName;
    String bondId;
    double percentageChange;
    double openPrice;
    double lastTransactionPrice;
    Time lastChanged;

    @Override
    public void setSecurityName(String securityName) {
        this.bondName = securityName;
    }

    @Override
    public void setLastTransactionPrice(String closePrice) {
        this.lastTransactionPrice = 
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
    public void setSecurityId(String securityId) {
        this.bondId = securityId;
    }

    @Override
    public void setLastChangedTime(String when) {
        try {
            String time = when.equals("--") ? "00:00:00" : when + ":00";
            this.lastChanged = Time.valueOf(time);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(when);
        }
    }

    @Override
    public double getChange() {
        return percentageChange;
    }

    @Override
    public String getSecurityId() {
        return bondId;
    }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, bondName) + " " 
            + String.format(OUTPUT_STRING_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
            + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }
    
    @Override
    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, bondName) + " "
                + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + "%";
    }

    @Override
    public void setLop(String lop) {}

    @Override
    public void setLopChange(String lopChange) {}

    @Override
    public void setVolume(String volume) {}
    
    @Override
    public void setHigh(String high) {}

    @Override
    public void setLow(String low) {}

    @Override
    public void setExpirationDate(String date) {}

}
