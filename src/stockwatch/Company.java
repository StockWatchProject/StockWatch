package stockwatch;

public class Company {

    private final static String OUTPUT_STRING_FORMAT = "%15s";
    private final static int UNDEFINED_VALUE = 9999;

    private String companyName;
    private String lastTransactionPrice;
    private String openPrice;
    private String percentageChange;
    private String companyId;
    private String lastChanged;

    private class CompanyData {
        public int companyId;
        public double percentageChange;
        public double openPrice;
        public double lastTransactionPrice;
    }

    private CompanyData data;

    Company() {
        companyName = "";
        lastTransactionPrice = "";
        openPrice = "";
        percentageChange = "";
        companyId = "";
        lastChanged = "";

        data = new CompanyData();
    }

    Company(String name, String close, String open, String change, String id) {
        companyName = name;
        lastTransactionPrice = close;
        openPrice = open;
        percentageChange = change;
        companyId = id;
        lastChanged = " ";
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLastTransactionPrice(String closePrice) {
        this.lastTransactionPrice = closePrice;
        data.lastTransactionPrice = 
            closePrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(closePrice.trim().replace(',', '.'));
    }

    public void setOpenPrice(String openPrice) {
        this.openPrice = openPrice;
        data.openPrice = 
            openPrice.equals("--") ? UNDEFINED_VALUE : Double.parseDouble(this.openPrice.trim().replace(',', '.'));
    }

    public void setPercentageChange(String percentageChange) {
        this.percentageChange = percentageChange;
        data.percentageChange = 
            percentageChange.equals("--") ? 0 : Double.parseDouble(percentageChange.trim().replace(',', '.'));
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
        data.companyId = 
            companyId.equals("--") ? UNDEFINED_VALUE : Integer.parseInt(companyId.trim());
    }
    
    public void setLastChanged(String when) {
        this.lastChanged = when;
    }

    public String getCompanyId() {
        return companyId;
    }
    
    public double getChange() {
        return data.percentageChange;
    }

    @Override
    public String toString() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " " 
                + String.format(OUTPUT_STRING_FORMAT, openPrice) + " " 
                + String.format(OUTPUT_STRING_FORMAT, lastTransactionPrice) + " "
                + String.format(OUTPUT_STRING_FORMAT, lastChanged) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }

    public String sessionResult() {
        return String.format(OUTPUT_STRING_FORMAT, companyName) + " "
                + String.format(OUTPUT_STRING_FORMAT, percentageChange);
    }

}
