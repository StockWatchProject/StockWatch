package stockwatch.stockmarkets.descriptions;

import stockwatch.stockmarkets.MarketNames;

public interface IStockMarketDescription {
    MarketNames getName();
    IMarketTypes[] getInternalMarkets();
}
