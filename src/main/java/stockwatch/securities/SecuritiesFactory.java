package stockwatch.securities;

import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class SecuritiesFactory {

    public Security getSecurity(IMarketTypes type) throws IllegalArgumentException {
        SecurityTypes securityType = type.getSecurityType();
        switch (securityType) {
            case Stock:
                return getStock();
            case Bond:
                return getBond();
            case FuturesContract:
                return getFutureContract();
            case Option:
                return getOption();
            case Index:
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
