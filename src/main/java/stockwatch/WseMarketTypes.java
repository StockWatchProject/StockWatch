/**
 * 
 */
package stockwatch;

/**
 * Enum which contains all internal types of markets of Warsaw Stock Exchange.
 * Additional info (page address is given for parsing purposes)
 */
public enum WseMarketTypes {
    MainMarket("http://www.parkiet.com/temat/63.html"), 
    NewConnect("http://www.parkiet.com/temat/68.html");
    // Catalyst,
    // Futures,
    // Options,
    // Warrants

    private WseMarketTypes(String pageAddr) {
        this.pageAddr = pageAddr;
    }

    public String getAddress() {
        return pageAddr;
    }

    private String pageAddr;

}
