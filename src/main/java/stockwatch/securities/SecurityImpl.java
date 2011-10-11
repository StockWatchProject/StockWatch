package stockwatch.securities;

import java.sql.Time;

public abstract class SecurityImpl implements ISecurity {
    String securityName;
    String securityId;
    double percentageChange;
    double openPrice;
    double lastTransactionPrice;
    double high;
    double low;
    int volume;
    Time lastChanged;

    public SecurityImpl() {
        securityName = new String();
        securityId = new String();
    }

    @Override
    public void setSecurityName(String companyName) {
        this.securityName = companyName;
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
        this.securityId = companyId;
    }

    @Override
    public void setLastChangedTime(String when) {
        try {
            String time = when.equals("--") ? "00:00:00" : when + ":00";
            this.lastChanged = Time.valueOf(time);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
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
    public String getSecurityId() {
        return securityId;
    }

    @Override
    public double getChange() {
        return percentageChange;
    }

    @Override
    public String sessionResult() {
        return String.format(OUTPUT_FORMAT, securityName) + " "
            + String.format(OUTPUT_FORMAT, percentageChange) + "%";
    }
    
    public abstract void setLop(String lop);

    public abstract void setLopChange(String lopChange);

    public abstract void setExpirationDate(String date);

    public abstract String toString();
}
