package stockwatch.securities;

import stockwatch.messages.QuoteMessages.Quote;

public interface ISecurity {
    public final static String OUTPUT_FORMAT = "%20s";
    public final static int UNDEFINED_VALUE = 0;
    
    public void setSecurityName(String securityName);
    public String getSecurityName();
    
    public void setLastTransactionPrice(String closePrice);
    public double getLastTransactionPrice();
    
    public void setOpenPrice(String openPrice);
    public double getOpenPrice();
    
    public void setPercentageChange(String percentageChange);
    public double getPercentageChange();
    
    public void setSecurityId(String securityId);
    public String getSecurityId();
    
    public void setLastChangedTime(String when);
    public String getLastChangedTime();
    
    public void setHigh(String high);
    public double getHigh();
    
    public void setLow(String low);
    public double getLow();
    
    public void setLop(String lop);
    public int getLop();
    
    public void setLopChange(String lopChange);
    public int getLopChange();
    
    public void setVolume(String volume);
    public int getVolume();
    
    public void setExpirationDate(String date);
    public String getExpirationDate();

    public String toString();
    public String sessionResult();
    
    public int getMarketId();
    
    public Quote fullSerialize();
    public Quote simpleSerialize();
}