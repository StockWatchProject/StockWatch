package stockwatch.securities;

import java.sql.Time;


public class FutureContract implements Security {

    String futureContractName;
    String futureContractId;
    double percentageChange;
    double openPrice;
    double lastTransactionPrice;
    double high;
    double low;
    int lop;
    int lopChange;
    int volume;
    Time lastChanged;

    public FutureContract() {
        futureContractName = "";
    }
    
    @Override
    public void setSecurityName(String securityName) {
        this.futureContractName = securityName;
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
                percentageChange.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    @Override
    public void setSecurityId(String securityId) {
        this.futureContractId = securityId;

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
    public double getChange() {
        return this.percentageChange;
    }

    @Override
    public String getSecurityId() {
        return this.futureContractId;
    }

    @Override
    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, futureContractName) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange) + "%";
    }

    @Override
    public void setLop(String lop) {
        this.lop = 
                lop.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(lop.trim());
    }

    @Override
    public void setLopChange(String lopChange) {
        this.lopChange = 
                lopChange.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(lopChange.trim().replace("+", ""));
    }

    @Override
    public void setVolume(String volume) {
        this.volume = 
                volume.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(volume.replace(" ", ""));
    }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, futureContractName) + " " 
            + String.format(OUTPUT_STRING_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
            + String.format(OUTPUT_STRING_FORMAT, percentageChange) + " "
            + String.format(OUTPUT_STRING_FORMAT, volume);
    }

    @Override
    public void setExpirationDate(String date) {}

}
