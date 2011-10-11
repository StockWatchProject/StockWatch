package stockwatch.securities;

import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class SecuritiesFactory {

    public ISecurity getSecurity(IMarketTypes type) throws IllegalArgumentException {
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

    ISecurity getStock() {
        return new Share();
    }

    ISecurity getBond() {
        return new Bond();
    }

    ISecurity getFutureContract() {
        return new FutureContract();
    }
    
    ISecurity getOption() {
        return new Option();
    }
    
    ISecurity getIndex() {
        return new Index();
    }
}
