package stockwatchTests;

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

import stockwatch.InternalMarkets;
import stockwatch.Security;
import stockwatch.Share;
import stockwatch.WseInternalMarkets;
import stockwatch.WseMarketTypes.EWseMarketTypes;

public class WseInternalMarketsTest extends TestCase {

    WseInternalMarkets wseInternalMarkets;
    
    @Before
    public void setUp() throws Exception {
        wseInternalMarkets = new WseInternalMarkets();
    }

    @Test
    public void testGetQuotes() {
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        assertEquals(allMarkets.length, wseInternalMarkets.getQuotes().size());
    }

    @Test
    public void testGetStatistics() {
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        assertEquals(allMarkets.length, wseInternalMarkets.getStatistics().size());
    }

    @Test
    public void testUpdateMarkets() {
        InternalMarkets mockedIntMarkets = mock(InternalMarkets.class);
        
        Map<String, Vector<Security>> internalMarkets = new HashMap<String, Vector<Security>>();
        Vector<Security> securities = new Vector<Security>();

        for (int i = 0; i < 10; i++)
            securities.add(new Share());
        
        EWseMarketTypes allMarkets[] = EWseMarketTypes.values();
        for (EWseMarketTypes market : allMarkets) {
            internalMarkets.put(market.name(), securities);
        }
        
        when(mockedIntMarkets.getQuotes()).thenReturn(internalMarkets);
        
        wseInternalMarkets.updateMarkets(mockedIntMarkets);
        
        verify(mockedIntMarkets, only()).getQuotes();
    }

}
