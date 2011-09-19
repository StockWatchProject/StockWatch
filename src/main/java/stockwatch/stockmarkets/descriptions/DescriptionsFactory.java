package stockwatch.stockmarkets.descriptions;


import stockwatch.stockmarkets.MarketNames;

public class DescriptionsFactory {
    static public IStockMarketDescription getDescription(MarketNames mName){
        String ClassName = "stockwatch.stockmarkets.descriptions." + mName.name() + "Description";
        try {
            return (IStockMarketDescription) Class.forName(ClassName).newInstance();
            //TODO: better exception handling
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
}
