package stockwatch.stockmarkets.parsers;

import java.lang.reflect.InvocationTargetException;
import java.util.Vector;

import stockwatch.stockmarkets.InternalMarket;
import stockwatch.stockmarkets.descriptions.IStockMarketDescription;

public class QuotesParsersFactory {
    @SuppressWarnings("unchecked")
    static public QuotesParser getParser(IStockMarketDescription marketDesc,Vector<InternalMarket> im){
        String parserClassName = "stockwatch.stockmarkets.parsers." + marketDesc.getName().name() + "Parser";
        Class[] aTypes = { Vector.class };
        Object[] aArgs = { im };
        try {
            return (QuotesParser) Class.forName(parserClassName).getConstructor(aTypes).newInstance(aArgs);
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
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
