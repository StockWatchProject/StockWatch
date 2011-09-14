package stockwatch.securities;

import stockwatch.stockmarkets.descriptions.IMarketTypes;
import stockwatch.stockmarkets.descriptions.WSEDescription.MarketTypes;

public class SecuritiesFactory {

    public Security getSecurity(IMarketTypes type) throws IllegalArgumentException {
        //TODO: get rid of this horrible casting :[
        MarketTypes marketType = (MarketTypes) type;
        switch (marketType) {
            case MainMarket:
                return getStock();
            case NewConnect:
                return getStock();
            case Catalyst:
                return getBond();
            case Futures:
                return getFutureContract();
            case Options:
                return getOption();
            case Indexes:
                return getIndex();
            default:
                throw new IllegalArgumentException("Wrong market type " + type);
        }
    }

    Security getStock() {
        return new Share();
    }

    Security getBond() {
        return new Bond();
    }

    Security getFutureContract() {
        return new FutureContract();
    }
    
    Security getOption() {
        return new Option();
    }
    
    Security getIndex() {
        return new Index();
    }
}
