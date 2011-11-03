package stockwatch.stockmarkets.descriptions;


import org.apache.log4j.Logger;

import stockwatch.stockmarkets.MarketNames;

public class DescriptionsFactory {
    private static final Logger logger = Logger.getLogger(DescriptionsFactory.class);
    
    static public IStockMarketDescription getDescription(MarketNames mName){
        String className = "stockwatch.stockmarkets.descriptions." + mName.name() + "Description";
        //TODO: After migration to jdk7 change this chain of catch to only one exception:  ReflectiveOperationException
        try {
            IStockMarketDescription newDesc = (IStockMarketDescription) Class.forName(className).newInstance();
            logger.debug(newDesc.getClass().getSimpleName() + " has risen.");
            return newDesc;
        } catch (ClassNotFoundException e) {
            logger.error(e);
        } catch (IllegalArgumentException e) {
            logger.error(e);
        } catch (SecurityException e) {
            logger.error(e);
        } catch (InstantiationException e) {
            logger.error(e);
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
        return null;
    }
}
