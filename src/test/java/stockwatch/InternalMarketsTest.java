package stockwatch;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import junit.framework.TestCase;

import org.junit.Before;
import org.junit.Test;

import stockwatch.securities.Security;
import stockwatch.securities.Share;
import stockwatch.stockmarkets.InternalMarkets;
import stockwatch.stockmarkets.descriptions.WSEDescription.MarketTypes;

public class InternalMarketsTest extends TestCase {

    InternalMarkets internalMarkets;
    
    @Before
    public void setUp() throws Exception {
        internalMarkets = new InternalMarkets();
    }

    @Test
    public void testGetQuotes() {
        MarketTypes allMarkets[] = MarketTypes.values();
        assertEquals(allMarkets.length, internalMarkets.getQuotes().size());
    }

    @Test
    public void testGetStatistics() {
        MarketTypes allMarkets[] = MarketTypes.values();
        assertEquals(allMarkets.length, internalMarkets.getStatistics().size());
    }

    @Test
    public void testUpdateMarkets() {
        InternalMarkets mockedIntMarkets = mock(InternalMarkets.class);
        
        Map<String, Vector<Security>> internalMarketsTest = new HashMap<String, Vector<Security>>();
        Vector<Security> securities = new Vector<Security>();

        for (int i = 0; i < 10; i++)
            securities.add(new Share());
        
        MarketTypes allMarkets[] = MarketTypes.values();
        for (MarketTypes market : allMarkets) {
            internalMarketsTest.put(market.name(), securities);
        }
        
        when(mockedIntMarkets.getQuotes()).thenReturn(internalMarketsTest);
        
        
        verify(mockedIntMarkets, only()).getQuotes();
    }

}
