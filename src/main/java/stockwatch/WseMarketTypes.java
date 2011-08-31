/**
 * 
 */
package stockwatch;

class WseMarketTypes {
    
    static private final String[] mainMarketTags = { "zmiana", "czas", "o", "c" };
    static private final String[] newConnectTags = { "zmiana", "czas", "o", "c" };
//    /static private final String[] catalystTags = { "zmiana", "czas", "o", "c" };
    /**
     * Enumeration which contains all internal types of markets of Warsaw Stock
     * Exchange. Additional info (page address is given for parsing purposes)
     */
    static public enum EWseMarketTypes {
        MainMarket("http://www.parkiet.com/temat/63.html", mainMarketTags), 
        NewConnect("http://www.parkiet.com/temat/68.html", newConnectTags);
        //Catalyst("http://www.parkiet.com/temat/952893.html", catalystTags);
        // Futures,
        // Options,
        // Warrants

        private EWseMarketTypes(String pageAddr, String[] tags) {
            this.pageAddr = pageAddr;
            this.tags = tags;
        }

        public String getAddress() {
            return pageAddr;
        }
        
        public String [] getTags() {
            return tags;
        }

        private String pageAddr;
        private String[] tags;

    }

}
