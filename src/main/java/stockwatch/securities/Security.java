package stockwatch.securities;

public interface Security {

    public final static String OUTPUT_STRING_FORMAT = "%20s";
    public final static int UNDEFINED_VALUE = 0;
    
    public abstract void setSecurityName(String securityName);
    public abstract void setLastTransactionPrice(String closePrice);
    public abstract void setOpenPrice(String openPrice);
    public abstract void setPercentageChange(String percentageChange);
    public abstract void setSecurityId(String securityId);
    public abstract void setLastChangedTime(String when);
    public abstract void setHigh(String high);
    public abstract void setLow(String low);
    public abstract void setLop(String lop);
    public abstract void setLopChange(String lopChange);
    public abstract void setVolume(String volume);
    public abstract void setExpirationDate(String date);
    public abstract double getChange();
    public abstract String getSecurityId();
    public abstract String toString();
    public abstract String sessionResult();

}