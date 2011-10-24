package stockwatch.securities;

import stockwatch.messages.QuoteMessages.Quote;

public class FutureContract extends SecurityImpl {
    private final int marketId;
    private int lop;
    private int lopChange;
    
    protected FutureContract() { marketId = -1;}
    
    public FutureContract(int marketId) {
        this.marketId = marketId;
    }

    @Override
    public void setLop(String lop) {
        this.lop = lop.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(lop.trim());
    }
    
    @Override
    public int getLop() { return this.lop; }

    @Override
    public void setLopChange(String lopChange) {
        this.lopChange = lopChange.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(lopChange.trim().replace("+", ""));
    }

    @Override
    public int getLopChange() { return this.lopChange; }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_FORMAT, securityName) + " "
            + String.format(OUTPUT_FORMAT, securityId) + " "
            + String.format(OUTPUT_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_FORMAT, lastTransactionPrice) + " " 
            + String.format(OUTPUT_FORMAT, lastChanged) + " " 
            + String.format(OUTPUT_FORMAT, lop) + " "
            + String.format(OUTPUT_FORMAT, lopChange) + " " 
            + String.format(OUTPUT_FORMAT, volume) + " "
            + String.format(OUTPUT_FORMAT, percentageChange) + "%";
    }

    @Override
    public void setExpirationDate(String date) { }

    @Override
    public String getExpirationDate() { return null; }
    
    @Override
    public Quote fullSerialize() {
        return Quote.newBuilder()
                .setName(this.getSecurityName())
                .setId(this.getSecurityId())
                .setMarketId(this.getMarketId())
                .setLastPrice(this.getLastTransactionPrice())
                .setPercentageChange(this.getPercentageChange())
                .setOpen(this.getOpenPrice())
                .setLow(this.getLow())
                .setHigh(this.getHigh())
                .setVolume(this.getVolume())
                .setLastChangeTime(this.getLastChangedTime())
                .setLop(this.getLop())
                .setLopChange(this.getLopChange())
                .build();
    }
    
    @Override
    public int getMarketId() { return marketId; }
}
