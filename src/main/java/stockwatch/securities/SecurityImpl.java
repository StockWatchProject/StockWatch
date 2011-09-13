package stockwatch.securities;

import java.sql.Time;

public abstract class SecurityImpl implements Security {
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
    public final void setSecurityName(String companyName) {
        this.securityName = companyName;
    }

    @Override
    public final void setLastTransactionPrice(String closePrice) {
        this.lastTransactionPrice = 
            closePrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(closePrice.trim().replace(',', '.'));
    }

    @Override
    public final void setOpenPrice(String openPrice) {
        this.openPrice = 
            openPrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(openPrice.trim().replace(',','.'));
    }

    @Override
    public final void setPercentageChange(String percentageChange) {
        this.percentageChange = 
            percentageChange.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    @Override
    public final void setSecurityId(String companyId) {
        this.securityId = companyId;
    }

    @Override
    public final void setLastChangedTime(String when) {
        try {
            String time = when.equals("--") ? "00:00:00" : when + ":00";
            this.lastChanged = Time.valueOf(time);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println(when);
        }
    }
    
    @Override
    public final void setHigh(String high) {
        this.high =
            high.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(high.trim().replace(',', '.'));
    }

    @Override
    public final void setLow(String low) {
        this.low =
            low.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(low.trim().replace(',', '.'));
    }
    
    @Override
    public final void setVolume(String volume) {
        this.volume = 
             volume.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(volume.replace(" ", ""));
    }
    
    @Override
    public final String getSecurityId() {
        return securityId;
    }

    @Override
    public final double getChange() {
        return percentageChange;
    }

    @Override
    public final String sessionResult() {
        return String.format(OUTPUT_FORMAT, securityName) + " "
            + String.format(OUTPUT_FORMAT, percentageChange) + "%";
    }
    
    public abstract void setLop(String lop);

    public abstract void setLopChange(String lopChange);    

    public abstract void setExpirationDate(String date);

    public abstract String toString();
}
