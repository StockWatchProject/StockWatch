package stockwatch;

import java.sql.Time;

public class Index implements Security {

    String indexName;
    String indexId;
    double percentageChange;
    double openPrice;
    double lastTransactionPrice;
    double high;
    double low;
    double volume;
    Time lastChanged;
    
    @Override
    public void setSecurityName(String securityName) {
        this.indexName = securityName;
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
    public void setSecurityId(String securityId) {
        this.indexId = securityId;
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
    public void setVolume(String volume) {
        this.volume = 
                volume.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(volume.replace(" ", ""));
    }

    @Override
    public double getChange() {
        return this.percentageChange;
    }

    @Override
    public String getSecurityId() {
        return this.indexId;
    }

    @Override
    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, indexName) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange) + "%";
    }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, indexName) + " " 
            + String.format(OUTPUT_STRING_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
            + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }
    
    @Override
    public void setLop(String lop) {}

    @Override
    public void setLopChange(String lopChange) {}
    
    @Override
    public void setExpirationDate(String date) {}

}
