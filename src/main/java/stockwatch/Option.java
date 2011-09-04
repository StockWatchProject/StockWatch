package stockwatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Option extends FutureContract {
    Date expirationDate;
    static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    
    public Date getExpirationDate() {
        return expirationDate;
    }

    @Override
    public void setExpirationDate(String expirationDate) {
        try {
            this.expirationDate = dateFormat.parse(expirationDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, super.futureContractName) + " " 
            + String.format(OUTPUT_STRING_FORMAT, dateFormat.format(expirationDate)) + " "
            + String.format(OUTPUT_STRING_FORMAT, super.openPrice) + " " 
            + String.format(OUTPUT_STRING_FORMAT, super.lastTransactionPrice) + " "
            + String.format(OUTPUT_STRING_FORMAT, super.lastChanged) + " "
            + String.format(OUTPUT_STRING_FORMAT, super.percentageChange);
    }
}
