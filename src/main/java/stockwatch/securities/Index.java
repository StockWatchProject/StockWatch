package stockwatch.securities;

public class Index extends SecurityImpl {
    private final int marketId;
    
    public Index(int marketId) {
        this.marketId = marketId;
    }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_FORMAT, securityName) + " " 
            + String.format(OUTPUT_FORMAT, securityId) + " "
            + String.format(OUTPUT_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_FORMAT, volume) + " "
            + String.format(OUTPUT_FORMAT, percentageChange) + "%";
    }
    
    @Override
    public void setLop(String lop) {}

    @Override
    public void setLopChange(String lopChange) {}
    
    @Override
    public void setExpirationDate(String date) {}

    @Override
    public int getLop() { return 0; }

    @Override
    public int getLopChange() { return 0; }

    @Override
    public String getExpirationDate() { return null; }
    
    @Override
    public int getMarketId() { return marketId; }
}
