package stockwatch.securities;

public class FutureContract extends SecurityImpl {
    int lop;
    int lopChange;

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
}
