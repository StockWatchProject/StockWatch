package stockwatch;

public interface Security {

    public abstract void setSecurityName(String companyName);
    public abstract void setLastTransactionPrice(String closePrice);
    public abstract void setOpenPrice(String openPrice);
    public abstract void setPercentageChange(String percentageChange);
    public abstract void setSecurityId(String companyId);
    public abstract void setLastChangedTime(String when);
    public abstract double getChange();
    public abstract String toString();
    public abstract String sessionResult();

}