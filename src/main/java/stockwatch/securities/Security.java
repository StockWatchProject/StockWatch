package stockwatch.securities;

public interface Security {

    public final static String OUTPUT_FORMAT = "%20s";
    public final static int UNDEFINED_VALUE = 0;
    
    public void setSecurityName(String securityName);
    public void setLastTransactionPrice(String closePrice);
    public void setOpenPrice(String openPrice);
    public void setPercentageChange(String percentageChange);
    public void setSecurityId(String securityId);
    public void setLastChangedTime(String when);
    public void setHigh(String high);
    public void setLow(String low);
    public void setLop(String lop);
    public void setLopChange(String lopChange);
    public void setVolume(String volume);
    public void setExpirationDate(String date);
    public double getChange();
    public String getSecurityId();
    public String toString();
    public String sessionResult();
}