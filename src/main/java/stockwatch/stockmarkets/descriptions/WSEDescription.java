package stockwatch.stockmarkets.descriptions;

import stockwatch.securities.SecurityTypes;
import stockwatch.stockmarkets.MarketNames;

public class WSEDescription implements IStockMarketDescription {
    
    static private final String[] mainMarketTags = { "zmiana", "czas", "o", "c", "h", "l", "obrot"};
    static private final String[] newConnectTags = { "zmiana", "czas", "o", "c", "h", "l", "obrot"};
    static private final String[] catalystTags   = { "zmiana", "czas", "c" };
    static private final String[] futuresTags    = { "zmiana", "czas", "o", "c", "lop", "obrot", "zmiana_lop", "l", "h"}; 
    static private final String[] optionsTags    = { "zmiana", "czas", "o", "c", "lop", "obrot", "zmiana_lop", "l", "h", "czas_wygasniecia"};
    static private final String[] indexesTags    = { "zmiana", "czas", "o", "c", "h", "l", "obrot"};
    
    MarketNames name = MarketNames.WSE;

    /**
     * Enumeration which contains all internal types of markets of Warsaw Stock
     * Exchange. Additional info (page address is given for parsing purposes)
     */
    static public enum MarketTypes implements IMarketTypes {
        MainMarket ("http://www.parkiet.com/temat/63.html",     mainMarketTags, SecurityTypes.Stock          ),
        NewConnect ("http://www.parkiet.com/temat/68.html",     newConnectTags, SecurityTypes.Stock          ),
        Catalyst   ("http://www.parkiet.com/temat/952893.html", catalystTags,   SecurityTypes.Bond           ),
        Futures    ("http://www.parkiet.com/temat/71.html",     futuresTags,    SecurityTypes.FuturesContract),
        Options    ("http://www.parkiet.com/temat/75.html",     optionsTags,    SecurityTypes.Option         ),
        Indexes    ("http://www.parkiet.com/temat/73.html",     indexesTags,    SecurityTypes.Index          );

        private MarketTypes(String pageAddr, String[] tags, SecurityTypes type) {
            this.pageAddr = pageAddr;
            this.tags = tags;
            this.securityType = type;
        }

        public String getPageAddress() {
            return pageAddr;
        }
        
        public String [] getTags() {
            return tags;
        }
        
        public String getName() {
            return this.name();
        }
        
        public IMarketTypes getType() {
            return this;
        }

        public SecurityTypes getSecurityType() {
            return securityType;
        }

        private String pageAddr;
        private String[] tags;
        private SecurityTypes securityType;

    }

    @Override
    public IMarketTypes[] getInternalMarkets() {
        return MarketTypes.values();
    }

    @Override
    public MarketNames getName() {
        return name;
    }
}
