package stockwatch.securities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import stockwatch.messages.QuoteMessages.Quote;

public class Option extends FutureContract {
    Date expirationDate;
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final int marketId;
    
    public Option(int marketId) {
        this.marketId = marketId;
    }

    @Override
    public void setExpirationDate(String expirationDate) {
        try {
            this.expirationDate = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            this.expirationDate = new Date();
            this.expirationDate.setTime(0);
        }
    }
    
    public String getExpirationDate() { return expirationDate.toString(); }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_FORMAT, super.securityName) + " " 
            + String.format(OUTPUT_FORMAT, super.securityId) + " " 
            + String.format(OUTPUT_FORMAT, dateFormat.format(expirationDate)) + " "
            + String.format(OUTPUT_FORMAT, super.openPrice) + " " 
            + String.format(OUTPUT_FORMAT, super.lastTransactionPrice) + " "
            + String.format(OUTPUT_FORMAT, super.lastChanged) + " "
            + String.format(OUTPUT_FORMAT, super.getLop()) + " "
            + String.format(OUTPUT_FORMAT, super.getLopChange()) + " "
            + String.format(OUTPUT_FORMAT, super.volume) + " "
            + String.format(OUTPUT_FORMAT, super.percentageChange) + "%";
    }
    
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
                .setExpirationDate(this.getExpirationDate().toString())
                .build();
    }
    
    @Override
    public int getMarketId() { return marketId; }
}
