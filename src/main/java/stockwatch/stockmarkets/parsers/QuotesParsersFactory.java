package stockwatch.stockmarkets.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import org.apache.log4j.Logger;

import stockwatch.stockmarkets.InternalMarket;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;

public class QuotesParsersFactory {
    private static final Logger logger = Logger.getLogger(QuotesParsersFactory.class);
    
    static public QuotesParser getParser(IStockMarketDescription marketDesc,Vector<InternalMarket> im) {
        String parserClassName = "stockwatch.stockmarkets.parsers." + marketDesc.getName().name() + "Parser";
        
        @SuppressWarnings("rawtypes")
        Class[] aTypes = { Vector.class };
        Object[] aArgs = { im };
        
        //TODO: After migration to jdk7 change this chain of catch to only one exception:  ReflectiveOperationException
        try {
            QuotesParser newParser = (QuotesParser) Class.forName(parserClassName).getConstructor(aTypes).newInstance(aArgs);
            logger.debug(newParser.getClass().getSimpleName() + " has risen.");
            return newParser;
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
        } catch (InvocationTargetException e) {
            logger.error(e);
        } catch (NoSuchMethodException e) {
            logger.error(e);
        } 
        return null;
    }
}
