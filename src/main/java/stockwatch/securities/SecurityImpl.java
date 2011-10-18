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

    @Override
    public void setSecurityName(String companyName) { this.securityName = companyName; }
    
    @Override
    public String getSecurityName() { return this.securityName; }

    @Override
    public void setSecurityId(String companyId) { this.securityId = companyId; }

    @Override
    public String getSecurityId() { return securityId; }
    
    @Override
    public void setPercentageChange(String percentageChange) {
        this.percentageChange = 
            percentageChange.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    @Override
    public double getPercentageChange() { return percentageChange; }
    
    @Override
    public void setOpenPrice(String openPrice) {
        this.openPrice = 
            openPrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(openPrice.trim().replace(',','.'));
    }

    @Override
    public double getOpenPrice() { return this.openPrice; }
    
    @Override
    public void setLastTransactionPrice(String closePrice) {
        this.lastTransactionPrice = 
            closePrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(closePrice.trim().replace(',', '.'));
    }
    
    @Override
    public double getLastTransactionPrice() { return this.lastTransactionPrice; }

    @Override
    public void setHigh(String high) {
        this.high =
            high.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(high.trim().replace(',', '.'));
    }

    @Override
    public double getHigh() { return this.high; }
    
    @Override
    public void setLow(String low) {
        this.low =
            low.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(low.trim().replace(',', '.'));
    }
    
    @Override
    public double getLow() { return this.low; }
    
    @Override
    public void setVolume(String volume) {
        this.volume = 
             volume.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(volume.replace(" ", ""));
    }
    
    @Override
    public int getVolume() { return this.volume; }
    
    @Override
    public void setLastChangedTime(String when) {
        try {
            String time = when.equals("--") ? "00:00:00" : when + ":00";
            this.lastChanged = Time.valueOf(time);
        } catch (IllegalArgumentException e) {
            this.lastChanged = Time.valueOf("00:00:00");
        }
    }
    
    @Override
    public String getLastChangedTime() { return this.lastChanged.toString(); }

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
