package stockwatch.securities;

import stockwatch.stockmarkets.descriptions.IMarketTypes;

public class SecuritiesFactory {

    public ISecurity getSecurity(IMarketTypes type, int marketId) throws IllegalArgumentException {
        SecurityTypes securityType = type.getSecurityType();
        switch (securityType) {
            case Stock:
                return getStock(marketId);
            case Bond:
                return getBond(marketId);
            case FuturesContract:
                return getFutureContract(marketId);
            case Option:
                return getOption(marketId);
            case Index:
                return getIndex(marketId);
            default:
                throw new IllegalArgumentException("Wrong market type " + type);
        }
    }

    ISecurity getStock(int marketId) {
        return new Share(marketId);
    }

    ISecurity getBond(int marketId) {
        return new Bond(marketId);
    }

    ISecurity getFutureContract(int marketId) {
        return new FutureContract(marketId);
    }
    
    ISecurity getOption(int marketId) {
        return new Option(marketId);
    }
    
    ISecurity getIndex(int marketId) {
        return new Index(marketId);
    }
}
