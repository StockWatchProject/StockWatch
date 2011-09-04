package stockwatch;

import java.sql.Time;

public class Share implements Security {

    String companyName;
    String companyId;
    double percentageChange;
    double openPrice;
    double lastTransactionPrice;
    double high;
    double low;
    Time lastChanged;

    public Share() {
        companyName = "";
    }

    public Share(String companyName, 
                  String companyId, 
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
            percentageChange.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    @Override
    public void setSecurityId(String companyId) {
        this.companyId = companyId;
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
    public void setHigh(String high) {
        this.high =
            high.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(high.trim().replace(',', '.'));
    }

    @Override
    public void setLow(String low) {
        this.low =
            low.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(low.trim().replace(',', '.'));
    }
    
    @Override
    public String getSecurityId() {
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

    @Override
    public void setLop(String lop) {}

    @Override
    public void setLopChange(String lopChange) {}

    @Override
    public void setVolume(String volume) {}

    @Override
    public void setExpirationDate(String date) {}

}
