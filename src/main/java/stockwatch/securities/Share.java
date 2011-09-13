package stockwatch.securities;

public class Share extends SecurityImpl {
    @Override
    public String toString() {
        return String.format(OUTPUT_FORMAT, securityName) + " " 
            + String.format(OUTPUT_FORMAT, securityId) + " "
            + String.format(OUTPUT_FORMAT, openPrice) + " " 
            + String.format(OUTPUT_FORMAT, lastTransactionPrice) + " "
            + String.format(OUTPUT_FORMAT, lastChanged) + " "
            + String.format(OUTPUT_FORMAT, volume) + " "
            + String.format(OUTPUT_FORMAT, percentageChange) + "%";
    }

    @Override
    public void setLop(String lop) {}

    @Override
    public void setLopChange(String lopChange) {}

    @Override
    public void setExpirationDate(String date) {}
}
